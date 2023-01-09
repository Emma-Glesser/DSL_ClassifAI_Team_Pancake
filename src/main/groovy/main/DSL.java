package main;

import java.io.File;

import dsl.ClassifAI_DSL;

class DSL {
    public static void main(String[] args) {
        ClassifAI_DSL dsl = new ClassifAI_DSL();
        if(args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
        }
    }
}