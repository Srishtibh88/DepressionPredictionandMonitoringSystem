import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
import re
from keras.models import Sequential
from keras.layers import Dense
from keras.preprocessing import sequence
#from sklearn import LabelEncoder
from sklearn.preprocessing import LabelEncoder
from sklearn.impute import SimpleImputer
from sklearn.utils import shuffle
import pickle
import math

#train set
df_train = pd.read_csv('train.csv')
# test set
df_test = pd.read_csv('test.csv')

#show the shape of the train dataframe
df_train.shape

def depression(x):
    if(x>0.5):
        return 1
    else:
        return 0

def missing_values_table(df):
        # Total missing values
        mis_val = df.isnull().sum()
        
        # Percentage of missing values
        mis_val_percent = 100 * df.isnull().sum() / len(df)
        
        # Make a table with the results
        mis_val_table = pd.concat([mis_val, mis_val_percent], axis=1)
        
        # Rename the columns
        mis_val_table_ren_columns = mis_val_table.rename(
        columns = {0 : 'Missing Values', 1 : '% of Total Values'})
        
        # Sort the table by percentage of missing descending
        mis_val_table_ren_columns = mis_val_table_ren_columns[
            mis_val_table_ren_columns.iloc[:,1] != 0].sort_values(
        '% of Total Values', ascending=False).round(1)
        
        # Print some summary information
        print ("The dataset has " + str(df.shape[1]) + " columns.\n"      
            "There are " + str(mis_val_table_ren_columns.shape[0]) +
              " columns that have missing values.")
        
        # Return the dataframe with missing information
        return mis_val_table_ren_columns


# Get the columns with > 50% missing
missing_df = missing_values_table(df_train);
missing_columns = list(missing_df[missing_df['% of Total Values'] > 50].index)
print('\n','%d columns will be deleted.' % len(missing_columns))

# Drop the columns with 50% missing data
df_train = df_train.drop(columns = list(missing_columns))


# Create a label (category) encoder object
encoder = LabelEncoder()

# fitting the encoder to the "survey_date" column
encoder.fit(df_train['survey_date'])

# Apply the fitted encoder to the "survey_date" to transform categories into integers
#encoded_train = encoder.transform(df_train['survey_date'])
encoded_train = encoder.transform(df_train['survey_date'],inplace=True)
# encoded_test = encoder.transform(df_test['survey_date'])

#assign the tranformed column back to the dataframe
df_train['survey_date'] = encoded_train

# split data into train and test sets
X = df_train.drop(["depressed"], axis=1)

# fill missing values with mean column values
imputer = SimpleImputer()
transformed_X = imputer.fit_transform(X)

y = df_train.depressed

seed = 5
test_size = 0.33
X_train, X_test, y_train, y_test = train_test_split(transformed_X, y, test_size=test_size, random_state=seed)


best=0
"""for _ in range(5):

    X_train, X_test, y_train, y_test = train_test_split(transformed_X, y, test_size=test_size, random_state=seed)


    # create model
    model = Sequential()
    model.add(Dense(36, input_dim=70, activation='relu'))
    model.add(Dense(36, activation='relu'))
    model.add(Dense(1, activation='sigmoid'))

    # Compile model
    model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

    # Fit the model
    model.fit(X_train, y_train, epochs=100, batch_size=10)

    scores = model.evaluate(X_test, y_test)
    #scores=model.score(X_test,y_test)
    print(scores[1])
    if scores[1]>best:
        best=scores[1]
        with open("historypredictionmodel.pickle","wb") as f:
            pickle.dump(model,f)"""
            
pickle_in=open("historypredictionmodel.pickle","rb")
model=pickle.load(pickle_in)
            
scores = model.evaluate(X_test, y_test)          
print("\n%s: %.2f%%" % (model.metrics_names[1], scores[1]*100))

predictions=model.predict(X_train)  # this will predict 

#print(X_test)

print(y_train)

for _ in range(5):
    print()

for x in range(len(predictions)): 
	print(depression(predictions[x]))#,'Actual',y_train[x])