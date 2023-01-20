package kernel.generator

enum AlgorithmImports {
    SVM_IMPORT( "    \"# Imports for SVM Algorithm\\n\",\n" +
                "    \"from sklearn.model_selection import GridSearchCV\\n\",\n"),

    KNN_IMPORT( "    \"# Imports for KNN Algorithm\\n\",\n" +
                "    \"from sklearn.neighbors import KNeighborsClassifier\\n\",\n" +
                "    \"from sklearn.metrics import accuracy_score"),

    RANDOM_FOREST_IMPORT(   "    \"# Imports for Random Forest Algorithm\\n\",\n" +
                            "    \"from sklearn.ensemble import RandomForestClassifier\\n\",\n" +
                            "    \"from sklearn.metrics import accuracy_score"),

    CNN_IMPORT( "    \"# Imports for CNN Algorithm\\n\",\n" +
                "    \"from keras import Input\\n\",\n" +
                "    \"from keras.models import Model\\n\",\n" +
                "    \"from keras.layers import Conv2D, Dense, Dropout, Flatten, MaxPooling2D, Normalization\\n\",\n" +
                "    \"from tensorflow.keras.utils import to_categorical"),

    PANDAS_IMPORT("    \"# Import for Pandas\\n\",\n" +
            "    \"import pandas\\n\"\n"),

    DATA_SELECTION_IMPORT("    \"# Import for data selection\\n\",\n" +
            "    \"from sklearn.model_selection import train_test_split\\n\"\n"),

    DATA_ACQUISTION_IMPORT("    \"# Import for data acquisition\\n\",\n" +
            "    \"import pandas as pd\\n\"\n"),

    DATA_PREPROCESSING_IMPORT("    \"# Import for data preprocessing\\n\",\n" +
    "    \"import numpy as np\\n\"\n");


    private final String value

    private AlgorithmImports(String value) {
        this.value = value
    }

    String getImports() {
        return this.value
    }


}