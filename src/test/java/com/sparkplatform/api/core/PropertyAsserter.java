package com.sparkplatform.api.core;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.junit.Assert.*;


/**
 * Test utility class that makes easy work of testing default behavior of getters and setters.
 * Brilliant idea from http://www.nearinfinity.com/blogs/scott_leberknight/do_you_unit_test_getters.html
 *
 * @author Scott Leberknight
 */
@SuppressWarnings("rawtypes")
public final class PropertyAsserter {

    private static final Log log = LogFactory.getLog(PropertyAsserter.class);

	private static final Map<Class, Object> TYPE_ARGUMENTS = new HashMap<Class, Object>();

    static {
        TYPE_ARGUMENTS.put(Collection.class, new ArrayList());
        TYPE_ARGUMENTS.put(List.class, new ArrayList());
        TYPE_ARGUMENTS.put(Set.class, new HashSet());
        TYPE_ARGUMENTS.put(SortedSet.class, new TreeSet());
        TYPE_ARGUMENTS.put(Map.class, new HashMap());
        TYPE_ARGUMENTS.put(SortedMap.class, new TreeMap());
        TYPE_ARGUMENTS.put(Boolean.class, true);
        TYPE_ARGUMENTS.put(Boolean.TYPE, true);
        TYPE_ARGUMENTS.put(Character.class, 'Z');
        TYPE_ARGUMENTS.put(Character.TYPE, 'Z');
        TYPE_ARGUMENTS.put(Byte.class, (byte) 10);
        TYPE_ARGUMENTS.put(Byte.TYPE, (byte) 10);
        TYPE_ARGUMENTS.put(Short.class, (short) 10);
        TYPE_ARGUMENTS.put(Short.TYPE, (short) 10);
        TYPE_ARGUMENTS.put(Integer.class, 10);
        TYPE_ARGUMENTS.put(Integer.TYPE, 10);
        TYPE_ARGUMENTS.put(Long.class, 10L);
        TYPE_ARGUMENTS.put(Long.TYPE, 10L);
        TYPE_ARGUMENTS.put(Float.class, 3.14159F);
        TYPE_ARGUMENTS.put(Float.TYPE, 3.14159F);
        TYPE_ARGUMENTS.put(Double.class, 3.14159);
        TYPE_ARGUMENTS.put(Double.TYPE, 3.14159);
        TYPE_ARGUMENTS.put(java.sql.Date.class, new java.sql.Date(new Date().getTime()));
        TYPE_ARGUMENTS.put(Timestamp.class, new Timestamp(new Date().getTime()));
        TYPE_ARGUMENTS.put(Calendar.class, Calendar.getInstance());
    }

    private static final Map<Class, Object> DEFAULT_TYPE_ARGUMENTS =
            Collections.unmodifiableMap(new HashMap<Class, Object>(TYPE_ARGUMENTS));

    private static final int TEST_ARRAY_SIZE = 10;

    private PropertyAsserter() {
    }

    /**
     * Registers the specified type that will default to the speicifed <code>defaultArgument</code> as the argument to
     * setter methods. Note this method will override any existing default arguments for a type.
     *
     * @param type            the type to register
     * @param defaultArgument the default argument to use in setters
     */
    public static void registerTypeAndDefaultArgument(Class type, Object defaultArgument) {
        TYPE_ARGUMENTS.put(type, defaultArgument);
    }

    /**
     * Removes the specified type, so that there wil no longer be a default argument for the type.
     *
     * @param type the type to degister
     */
    public static void deregisterType(Class type) {
        TYPE_ARGUMENTS.remove(type);
    }

    /** Resets the types and default arguments. */
    public static void resetToDefaultTypes() {
        TYPE_ARGUMENTS.clear();
        TYPE_ARGUMENTS.putAll(DEFAULT_TYPE_ARGUMENTS);
    }

    /**
     * Returns the default argument for the specified type.
     *
     * @param type the type
     * @return the type's default argument
     */
    public static Object defaultArgumentForType(Class type) {
        return TYPE_ARGUMENTS.get(type);
    }

    /**
     * Tests that the getter and setter methods for <code>property</code> work in a basic fashion, which is that the
     * getter returns the exact same object as set by the setter method. (And we don't care that FindBugz says this is
     * bad, bad, bad and furthermore we disable that check in FindBugz anyway based on the Reduction of Java
     * Overengineering Act. Then again, some might argue that <i>this</i> class itself embodies Java Overengineering!)
     * <p/> Uses a default argument for basic collection types, primitive types, Dates, java.sql.Dates, and Timestamps.
     * See {@link PropertyAsserter#TYPE_ARGUMENTS}.
     *
     * @param target   the object on which to invoke the getter and setter
     * @param property the property name, e.g. "firstName"
     */
    public static void assertBasicGetterSetterBehavior(Object target, String property) {
        assertBasicGetterSetterBehavior(target, property, null);
    }

    /**
     * See {@link #assertBasicGetterSetterBehavior(Object,String)} method. Only difference is that here we accept an
     * explicit argument for the setter method.
     *
     * @param target   the object on which to invoke the getter and setter
     * @param property the property name, e.g. "firstName"
     * @param argument the property value, i.e. the value the setter will be invoked with
     */
    public static void assertBasicGetterSetterBehavior(Object target, String property, Object argument) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(property, target.getClass());
            Object arg = argument;
            Class type = descriptor.getPropertyType();
            if (arg == null) {
                if (type.isArray()) {
                    arg = Array.newInstance(type.getComponentType(), new int[]{ TEST_ARRAY_SIZE });
                }
                else if (type.isEnum()) {
                    arg = type.getEnumConstants()[0];
                }
                else if (TYPE_ARGUMENTS.containsKey(type)) {
                    arg = TYPE_ARGUMENTS.get(type);
                }
                else {
                    arg = invokeDefaultConstructorEvenIfPrivate(type);
                }
            }

            Method writeMethod = descriptor.getWriteMethod();
            Method readMethod = descriptor.getReadMethod();

            writeMethod.invoke(target, arg);
            Object propertyValue = readMethod.invoke(target);
            if (type.isPrimitive()) {
                assertEquals(property + " getter/setter failed test", arg, propertyValue);
            }
            else {
                assertSame(property + " getter/setter failed test", arg, propertyValue);
            }
        }
        catch (IntrospectionException e) {
            String msg = "Error creating PropertyDescriptor for property [" + property
                    + "]. Do you have a getter and a setter?";
            log.error(msg, e);
            fail(msg);
        }
        catch (IllegalAccessException e) {
            String msg = "Error accessing property. Are the getter and setter both accessible?";
            log.error(msg, e);
            fail(msg);
        }
        catch (InvocationTargetException e) {
            String msg = "Error invoking method on target";
            log.error(msg, e);
            fail(msg);
        }
    }

    @SuppressWarnings("unchecked")
    private static Object invokeDefaultConstructorEvenIfPrivate(Class type) {
        try {
			Constructor ctor = type.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not invoke default constructor on type " + type, ex);
        }
    }

    /**
     * See {@link #assertBasicGetterSetterBehavior(Object,String)} method. Only difference is that here we accept a map
     * containing property name/value pairs. Use this to test a bunch of property accessors at once. Note that the
     * values in the map can be null, and in that case we'll try to supply a default argument.
     *
     * @param target     the object on which to invoke the getter and setter
     * @param properties map of property names to argument values
     */
    public static void assertBasicGetterSetterBehavior(Object target, Map<String, Object> properties) {
        Set<Map.Entry<String, Object>> entries = properties.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            assertBasicGetterSetterBehavior(target, entry.getKey(), entry.getValue());
        }
    }

    /**
     * See {@link #assertBasicGetterSetterBehavior(Object,String)} method. Only difference is that here we accept an
     * array of property names. Use this to test a bunch of property accessors at once, using default arguments.
     *
     * @param target        the object on which to invoke the getter and setter
     * @param propertyNames the names of the propertyes you want to test
     */
    public static void assertBasicGetterSetterBehavior(Object target, String... propertyNames) {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        for (String propertyName : propertyNames) {
            properties.put(propertyName, null);
        }
        assertBasicGetterSetterBehavior(target, properties);
    }

    /**
     * See {@link #assertBasicGetterSetterBehavior(Object, String[])} method. No items are blacklisted.
     *
     * @param target the object on which to invoke the getter and setter
     */
    public static void assertBasicGetterSetterBehavior(Object target) {
        assertBasicGetterSetterBehaviorWithBlacklist(target);
    }

    /**
     * See {@link #assertBasicGetterSetterBehavior(Object,String)} method. Big difference here is that we try to
     * automatically introspect the target object, finding read/write properties, and automatically testing the getter
     * and setter. Note specifically that read-only properties are ignored, as there is no way for us to know how to set
     * the value (since there isn't a public setter).
     * <p/>
     * Any property names contained in the blacklist will be skipped.
     * <p/>
     *
     * @param target        the object on which to invoke the getter and setter
     * @param propertyNames the list of property names that should not be tested
     */
    public static void assertBasicGetterSetterBehaviorWithBlacklist(Object target, String... propertyNames) {
        List<String> blacklist = Arrays.asList(propertyNames);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (descriptor.getWriteMethod() == null) {
                    continue;
                }
                if (!blacklist.contains(descriptor.getDisplayName())) {
                    assertBasicGetterSetterBehavior(target, descriptor.getDisplayName());
                }
            }
        }
        catch (IntrospectionException e) {
            fail("Failed while introspecting target " + target.getClass());
        }
    }

}