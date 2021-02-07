package com.pom.crimson.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/** Programmatically adds annotation to test methods.
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	/** Programmatically adds annotation to test methods.
	 * It adds max retry limit to a test case.
	 * @see <a href="https://testng.org/doc/documentation-main.html#annotationtransformers">https://testng.org/doc/documentation-main.html#annotationtransformers</a>
	 */
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
	//programmatically adds annotation to test methods
		annotation.setRetryAnalyzer(RetryAnalyzerWithMaxLimit.class);
	}

}
