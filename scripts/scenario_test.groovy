import static dsl.ClassifAI_DSL.*

program "scenario_test" being {

    dataProcessing {
        comment "Selection de 80% des données du jeu pour de l'entrainement et de 20% pour le test"
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
        visualizeDataset 'X_test'

    }

    algorithms {
        randomForest "RandomForest" definedAs {
            comment "Cet exemple montre une implémentation d'un algorithme de classification RandomForest"
            nbEstimators = 10
        }

        svm "SVM" comment "Cet exemple montre une implémentation d'un algorithme de classification SVM"


        knn "KNN" definedAs {
            comment "Cet exemple montre une implémentation d'un algorithme de classification KNN"
            k = 5
        }

        cnn "CNN" definedAs {
            comment "Cet exemple montre une implémentation d'un algorithme de classification CNN"
            epochs = 12
            batchSize = 15
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
                activationFunction = ActivationFunction.SOFTMAX
            }
        }
    }

    visualization {
        comment "Visualize the accuracy results of the algorithms KNN and SVM "
        comparison = Param.Accuracy
        algorithmsToVisualize = ["KNN", "SVM"]
    }
}
