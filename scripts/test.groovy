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
            comment"setsste"
            nb_estimators(5)
        }

        svm "test2" , {
            comment "sgtrtghj"
        }

        knn "testknn", {
            k(5)
            comment "sgtrtghj"
        }

        cnn "test" , {
            comment "sgtrtghj"
            normalizationLayer {
                comment "sgtrtghj"
            }
            convolutionLayer {
                input_shape([50,5,3])
                comment("azrer")
                padding(Padding.SAME)
            }
            dropoutLayer {
                rate_of_disabled_neurons(9)
                comment "sgtrtghj"
            }
            poolingLayer {
                strides([9,9,9])
                comment "sgtrtghj"
            }
            flattenLayer {
                comment "sgtrtghj"
            }
            denseLayer {
                units(5)
                comment "sgtrtghj"
            }
            comment "sgtrtghj"
        }
    }
}
