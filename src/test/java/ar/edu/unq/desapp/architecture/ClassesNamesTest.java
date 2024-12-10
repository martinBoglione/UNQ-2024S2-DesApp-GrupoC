package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ClassesNamesTest {

    private JavaClasses baseClasses;

    @BeforeEach
    public void setup() {
        baseClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("ar.edu.unq.desapp");
    }

    @Test
    void exceptionsClassesShouldEndWithException(){
        classes().that().resideInAPackage("..exceptions..")
                .should().haveSimpleNameEndingWith("Exception").check(baseClasses);
    }

    @Test
    void persistanceClassesShouldEndWithRepo(){
        classes().that().resideInAPackage("..repositories..")
                .should().haveSimpleNameEndingWith("Repository").check(baseClasses);
    }

    @Test
    void serviceClassesShouldEndWithService(){
        classes().that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service").check(baseClasses);
    }

    @Test
    void webserviceClassesShouldEndWithController(){
        classes().that().resideInAPackage("..webservice..")
                .should().haveSimpleNameEndingWith("Controller").check(baseClasses);
    }

}
