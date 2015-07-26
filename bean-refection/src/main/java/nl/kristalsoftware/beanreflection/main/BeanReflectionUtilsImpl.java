package nl.kristalsoftware.beanreflection.main;

import nl.kristalsoftware.beanreflection.exception.FieldConfigurationException;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BeanReflectionUtilsImpl implements BeanReflectionUtils {

    public BeanReflectionUtilsImpl() {
    }

    @Inject
    private Logger log;

    @Override
    public Field getField(Class<?> bean, String fieldName) throws NoSuchFieldException {
        Field f = bean.getDeclaredField(fieldName);
        return f;
    }

    @Override
    public List<Field> getNonStaticFields(Class<?> c) {
        Field[] fieldArr = c.getDeclaredFields();
        List<Field> list = new ArrayList<Field>();
        for (Field f : fieldArr) {
            if (!Modifier.isStatic(f.getModifiers())) {
                list.add(f);
                log.log(Level.FINE, f.toString());
            }
        }
        return list;
    }

    @Override
    public <A extends Annotation> Field getAnnotatedField(Class<?> bean, Class<A> annotation) {
        Field fld = null;
        Field[] fieldArr = bean.getDeclaredFields();
        for (Field f : fieldArr) {
            if (f.isAnnotationPresent(annotation)) {
                fld = f;
                break;
            }
        }
        return fld;
    }

    @Override
    public <A extends Annotation> List<Field> getAnnotatedFields(Class<?> bean, Class<A> annotation) {
        List<Field> annotatedFields = new ArrayList<Field>();
        Field[] fieldArr = bean.getDeclaredFields();
        for (Field f : fieldArr) {
            if (f.isAnnotationPresent(annotation)) {
                annotatedFields.add(f);
            }
        }
        return annotatedFields;
    }

    /*
    private <T> Object getFieldValue(T bean, Field field) throws IllegalAccessException {
        Object val = field.get(bean);
        return val;
    }
    */

    @Override
    public <T, V> V getPublicFieldValue(T bean, Field field, Class<V> clazz) throws IllegalAccessException {
        V val = (V) field.get(bean);
        return val;
    }

    @Override
    public <T, V> V getFieldValueWithGetter(T bean, Field field, Class<V> clazz) throws NoSuchMethodException {
        Class<?> c = bean.getClass();
        String fieldName = field.getName();
        log.fine(fieldName);
        Method method;
        String capital = fieldName.substring(0, 1).toUpperCase();
        method = c.getMethod("get" + capital + fieldName.substring(1));
        V val = null;
        try {
            val = (V) method.invoke(bean);
        } catch (IllegalAccessException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (InvocationTargetException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return val;
    }

    @Override
    public <T, V> void setPublicFieldValue(T bean, Field field, V value) throws IllegalAccessException {
        log.info(field.getType().toString());
        field.set(bean, value);
    }

    @Override
    public <T, V> void setFieldValueWithSetter(T bean, Field field, V val) throws NoSuchMethodException, FieldConfigurationException {
        Class<?> c = bean.getClass();
        String fieldName = field.getName();
        log.fine(fieldName);
        Method method;
        try {
            Class<?>[] ptypes = new Class<?>[1];
            ptypes[0] = val.getClass();
            method = c.getMethod("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1));
            log.fine(method.toString());
            method.invoke(bean, val);
        } catch (InvocationTargetException e) {
            throw new FieldConfigurationException(e);
        } catch (IllegalAccessException e) {
            throw new FieldConfigurationException(e);
        }
    }

        //
//	public Map<String, Field> createFieldsMap(List<Field> fieldList) {
//		Map<String,Field> fieldMap = new HashMap<String,Field>();
//		Iterator<Field> iter = fieldList.iterator();
//		while (iter.hasNext()) {
//			Field field = iter.next();
//			fieldMap.put(field.getName(), field);
//			Logger.debug(field.toString());
//		}
//		return fieldMap;
//	}
//
//	@Override
//	public <V,T> void setFieldValue(Field field, V value, T bean) {
//		int mod = field.getModifiers();
//		if (!Modifier.isPublic(mod)) {
//			field.setAccessible(true);
//		}
//		try {
//			field.set(bean, value);
//		} catch (Exception e) {
//			Logger.error(e.toString());
//		}
//	}
//
//	public <V,T> void setFieldValueWithSetter(Field field, V val, T bean) {
//		Class<?> c = bean.getClass();
//		String fieldName = field.getName();
//		Logger.debug(fieldName);
//		Method method;
//		try {
//			Class<?>[] ptypes = new Class<?>[1];
//			/*
//			Class<?> valClass = val.getClass();
//			TypeVariable<?>[] tvar = valClass.getTypeParameters();
//			if (tvar.length > 0) {
//				// parameterized class
//				valClass.get
//			}
//			else {
//				// non parameterized class
//			}
//			*/
//			ptypes[0] = val.getClass();
//			method = c.getMethod("set" + StringUtils.capitalize(fieldName), ptypes);
//			Logger.info(method.toString());
//			method.invoke(bean, val);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Logger.error(e.getMessage());
//		}
//	}
//
//	@Override
//	public <V,T> void setFieldCollectionValueWithSetter(Field field, Collection<V> val, T bean) {
//		Class<?> beanClass = bean.getClass();
//		Logger.debug("bean: " + beanClass.getName());
//		String fieldName = field.getName();
//		Logger.debug("fieldName: " + fieldName);
//		Class<?> valClass = val.getClass();	//ArrayList
//		Logger.debug("val: " + valClass.getName());
//		Method[] methodArr = beanClass.getMethods();
//		for (Method method : methodArr) {
//			if (method.getName().startsWith("set" + StringUtils.capitalize(fieldName))) {
//				Logger.info(method.toGenericString());
//				Type[] genParams = method.getGenericParameterTypes();
//				if (genParams.length > 0) {
//					Logger.debug(genParams[0].toString());
//					try {
//						method.invoke(bean, val);
//					} catch (Exception e) {
//						e.printStackTrace();
//						Logger.error(e.getMessage());
//					}
//				}
//			}
//		}
//
//		/*
//		Type[] interfaceArr = valClass.getGenericInterfaces();
//		for (Type interfaceType : interfaceArr) {
//			if (interfaceType instanceof ParameterizedType) {
//				Logger.info(interfaceType.getClass().getName());
//			}
//		}
//
//		TypeVariable<?>[] tvar = valClass.getTypeParameters();
//		Class<?> beanClass = bean.getClass();
//		try {
//			Class<?>[] ptypes = new Class<?>[1];
//			ptypes[0] = valClass;
//			Method method = beanClass.getMethod("set" + StringUtils.capitalize(fieldName), ptypes);
//			Logger.info("FieldName: " + fieldName + " - " + "Method: " + " " + method.toString());
//			method.invoke(bean, val);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Logger.error(e.getMessage());
//		}
//		*/
//	}
//
//	/*
//	@Override
//	public <V,T> void setFieldCollectionValueWithSetter(Field field, Collection<V> val, T bean) {
//		String fieldName = field.getName();
//		Class<?> valClass = val.getClass();
//		Type[] interfaceArr = valClass.getGenericInterfaces();
//		for (Type interfaceType : interfaceArr) {
//			Logger.info(interfaceType.getClass().toString());
//			if (interfaceType instanceof ParameterizedType) {
//				ParameterizedType ptype = (ParameterizedType) interfaceType;
//				String rawName = ptype.getRawType().toString();
//				Logger.info(rawName);
//				if (rawName.endsWith("Collection")) {
//					Class<?>[] ptypes = new Class<?>[1];
//					ptypes[0] = valClass;
//					//TypeVariable<?>[] tvar = valClass.getTypeParameters();
//					//String parameterName = tvar[0].getName();
//					Class<?> beanClass = bean.getClass();
//					try {
//						Method method = beanClass.getMethod("set" + StringUtils.capitalize(fieldName), ptypes);
//						Logger.info("FieldName: " + fieldName + " - " + "Method: " + " " + method.toString());
//						method.invoke(bean, val);
//					} catch (Exception e) {
//						e.printStackTrace();
//						Logger.error(e.getMessage());
//					}
//				}
//			}
//		}
//	}
//	*/

    }
