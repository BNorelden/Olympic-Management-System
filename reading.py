import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

import pandas as pd

cred = credentials.Certificate("../Firestore/riomanagement-58e49-firebase-adminsdk-wtrt1-fd786a545a.json")
firebase_admin.initialize_app(cred)

db = firestore.client()

eventArr = []
discipline = []
eventName = []

events_df = pd.read_csv("../Firestore/events.csv")
athletes_df = pd.read_csv("../Firestore/athletes.csv")

for i in events_df.index.values:
    
    eventArr.append(events_df.iloc[i, 1])
    discipline.append(events_df.iloc[i, 2])
    eventName.append(events_df.iloc[i, 3])


#doc_ref = db.collection(u'CSV-Events')

for i in range(0, 300):
    
    doc_ref = db.collection(u'CSV-Events').document(eventArr[i]).collection(discipline[i]) .document(eventName[i])
    try:
        doc = doc_ref.get()
        print(u'Document data: {}'.format(doc.to_dict()))
        
    except google.cloud.exceptions.NotFound:
        print(u'No such document!')

#print(eventArr[1])