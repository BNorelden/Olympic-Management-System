import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

import pandas as pd

events_df = pd.read_csv("../Firestore/events.csv")

cred = credentials.Certificate("../Firestore/riomanagement-58e49-firebase-adminsdk-wtrt1-fd786a545a.json")
firebase_admin.initialize_app(cred)

db = firestore.client()

for i in events_df.index.values:
    
    # create a document of sport in collection CSV_events
    # sport, discipline, name
    doc_ref = db.collection(u'CSV-Events').document(events_df.iloc[i, 1]).collection(events_df.iloc[i, 2]).document(events_df.iloc[i, 3])
    
    
    doc_ref.set({   
        u'sport' : events_df.iloc[i, 1],
        u'discipline' : events_df.iloc[i, 2],
        u'Event Name' : events_df.iloc[i, 3],
        u'sex' : events_df.iloc[i, 4],   
        u'Venue' : events_df.iloc[i, 5]
        })