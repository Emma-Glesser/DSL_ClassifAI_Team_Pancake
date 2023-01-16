import static dsl.ClassifAI_DSL.*

program "test" being {

    imports {
        import_librairies {

        }
    }


    visualization {

    }

    dataProcessing {

    }

    codes {
        comment"liugiytgfi"

    }

    algorithms {
        randomForest "test", {
            nb_estimators(5)
        }

        svm "test2" , {}

        knn "testknn", {
            k(5)
        }
    }
}
