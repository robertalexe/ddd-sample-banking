package com.robert.ddd;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class DDDComponentScanReport {

    public static void main(String[] args) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);



        scanner.addIncludeFilter(new AnnotationTypeFilter(DDD.ApplicationService.class));

        System.out.println("************ Application Services ************");
        for (BeanDefinition bd : scanner.findCandidateComponents("com.robert.ddd")) {
            System.out.println(bd.getBeanClassName());
        }
        scanner.addExcludeFilter(new AnnotationTypeFilter(DDD.ApplicationService.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(DDD.Entity.class));
        System.out.println("************ Entities ************");
        for (BeanDefinition bd : scanner.findCandidateComponents("com.robert.ddd")) {
            System.out.println(bd.getBeanClassName());
        }
    }
}
