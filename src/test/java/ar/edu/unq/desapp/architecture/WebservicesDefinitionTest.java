package ar.edu.unq.desapp.architecture;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

public class WebservicesDefinitionTest {

    private JavaClasses baseClasses;

    @BeforeEach
    public void setup() {
        baseClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("ar.edu.unq.desapp");
    }

    @Test
    void controllerClassesShouldHaveSpringControllerAnnotation() {
        classes().that().resideInAPackage("..webservice..")
                .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestController")
                .check(baseClasses);
    }

    @Test
    void methodsWithAnyRequestMappingAnnotationShouldHaveLogExecutionTimeAnnotation() {
        methods()
                .that().areDeclaredInClassesThat().resideInAPackage("..webservice..")
                .and().arePublic()
                .and()
                .areAnnotatedWith("org.springframework.web.bind.annotation.GetMapping")
                .or().areAnnotatedWith("org.springframework.web.bind.annotation.PostMapping")
                .or().areAnnotatedWith("org.springframework.web.bind.annotation.PutMapping")
                .or().areAnnotatedWith("org.springframework.web.bind.annotation.PatchMapping")
                .or().areAnnotatedWith("org.springframework.web.bind.annotation.DeleteMapping")
                .should().beAnnotatedWith(LogExecutionTime.class)
                .check(baseClasses);
    }

}
