import static dsl.ClassifAI_DSL.*

program "test" being {

    dataProcessing {
        acquisition {
            filePath = "test"
            setName = "bonjour"
        }
        selection {
            testSize = 12
            shuffleData = false
        }
        preprocessing {
            reshape = [1, 16, 16]
            normalize = 6
        }

        visualizeDataset 'X_train'
    }

    algorithms {
        randomForest "test", {
            nb_estimators = 5
        }

        svm "test2" , {
        }

        knn "test_knn", {
            k = 5
        }

        cnn "test" , {
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
        comparison = [Param.Accuracy, Param.ExecTime]
        algorithmsToVisualize = ["test", "test2"]
    }
}
