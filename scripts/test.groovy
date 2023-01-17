import static dsl.ClassifAI_DSL.*

program "test" being {

    imports {
        import_librairies {
            lib "math"
        }
    }


    visualization {

    }

    dataProcessing {

    }

    algorithms {
        randomForest "test", {
            nb_estimators(5)
        }

        svm "test2" , {
        }

        knn "testknn", {
            k(5)
        }

        cnn "test" , {
            normalizationLayer {
            }
            convolutionLayer {
                input_shape([50,5,3])
                padding(Padding.SAME)
            }
            dropoutLayer {
                rate_of_disabled_neurons(9)
            }
            poolingLayer {
                strides([9,9,9])
            }
            flattenLayer {
            }
            denseLayer {
                units(5)
            }
        }
    }
}
