package models;

/**
 * This Annotation serves as an interface
 * for keeping the database columns in line with model fields.
 * @author Ian Wilhelmsen
 * last updated: 4/30/2020
 */
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE, METHOD, PARAMETER, FIELD})
public @interface ModelAnnotations {
	public String key();
	public String value();
}
