import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

import pandas as pd

athlete_df = pd.read_csv("../Firestore/athletes.csv")

cred = credentials.Certificate("../Firestore/riomanagement-58e49-firebase-adminsdk-wtrt1-fd786a545a.json")
firebase_admin.initialize_app(cred)

db = firestore.client()

for i in range(0, 10):
    
    #add name of user a document to collection CSV
    doc_ref = db.collection(u'CSV').document(athlete_df.iloc[i, 1])
    
    #Setting values of each row and it's columns to document created above
    doc_ref.set({   
        u'name' : athlete_df.iloc[i, 1],
        u'nation' : athlete_df.iloc[i, 2],
        u'sex' : athlete_df.iloc[i, 3],
        u'dob' : athlete_df.iloc[i, 4],   
        u'height' : athlete_df.iloc[i, 5],
        u'weight' : athlete_df.iloc[i, 6]
            })

#print(athlete_df.iloc[0, 1])