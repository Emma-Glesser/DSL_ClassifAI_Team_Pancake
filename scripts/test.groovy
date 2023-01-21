import static dsl.ClassifAI_DSL.*

program "test" being {

    dataProcessing {
        comment "machin chouette"
        acquisition {
            filePath = "test"
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
        algorithmsToVisualize = ["KNN", "SVM"]
    }
}
