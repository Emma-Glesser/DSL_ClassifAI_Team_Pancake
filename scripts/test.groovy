import static dsl.ClassifAI_DSL.*

program "test" being {

    dataProcessing {
        comment "machin chouette"
        acquisition {
            filePath = "/data/workspace_files/mnist_test.csv"
        }
        selection {
            testSize = 0.2
            shuffleData = false
        }
        preprocessing {
            reshape = [28, 28, 1]
            normalize = 6
        }

        visualizeDataset 'X_train'
    }

    algorithms {
        randomForest "randomForest" definedAs {
            nbEstimators = 10
        }

        svm "SVM"

        knn "KNN" definedAs {
            k = 5
        }

        cnn "CNN" definedAs {
            epochs = 12
            batch_size = 15
            convolutionLayer {
                filters = 32
                kernelSize = [5, 5]
                padding = Padding.SAME
                activationFunction = ActivationFunction.RELU
            }

            normalizationLayer()

            convolutionLayer {
                filters = 32
                kernelSize = [5, 5]
                padding = Padding.SAME
                activationFunction = ActivationFunction.RELU
            }

            normalizationLayer()

            poolingLayer {
                strides = [2,2]
            }

            dropoutLayer {
                rateOfDisabledNeurons = 0.25
            }
            flattenLayer()

            denseLayer {
                units = 10
                activation_function = ActivationFunction.SOFTMAX
            }
        }
    }

    visualization {
        comment("Visualize the results of the algorithms")
        comparison = Param.Accuracy
        algorithmsToVisualize = ["KNN", "SVM"]
    }

    visualization {
        comment("Visualize the results of the algorithms")
        comparison = Param.Accuracy
        algorithmsToVisualize = ["KNN", "SVM"]
    }
}
