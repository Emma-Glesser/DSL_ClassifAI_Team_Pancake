import static dsl.ClassifAI_DSL.*

program "test" being {

    dataProcessing {
        comment "ZF<EGZ"
        acquisition {
            filePath = "test"
            setName = "bonjour"
        }
        selection {
            testSize = 0.2
            shuffleData = false
        }
        preprocessing {
            reshape = [1, 16, 16]
            normalize = 6
        }

        visualizeDataset 'X_train'
    }

    algorithms {
        randomForest "randomForest", {
            nb_estimators = 5
        }

        svm "svm" , {
        }

        knn "knn", {
            k = 5
        }

        cnn "cnn" , {
            epochs = 12
            batch_size = 15

            normalizationLayer()
            convolutionLayer {
                filters = 32
                kernelSize = [3, 3]
                padding = Padding.SAME
                activationFunction = ActivationFunction.RELU
            }
            dropoutLayer {
                rateOfDisabledNeurons = 0.5
            }
            poolingLayer {
                strides = [5,5]
            }
            flattenLayer()

            denseLayer {
                units = 5
                activation_function = ActivationFunction.RELU
            }
        }
    }

    visualization {
        comparison = Param.Accuracy
        algorithmsToVisualize = ["knn", "svm"]
    }
}
