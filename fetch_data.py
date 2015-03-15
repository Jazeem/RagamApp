import sqlite3
import httplib
import json
conn = httplib.HTTPConnection("www.ragam.org.in")
db = sqlite3.connect('app/src/main/assets/ragam.db')
total=0;

def storeEvents(type,genre,events):
	global total;
	url='/2015/cms/api/event/'
	for event in events:
		conn.request("GET",url+event['event_code'])
		event_response = conn.getresponse()
		event_data = json.loads(event_response.read())	
		values=[];
		code=event_data['event_code']
		name=event_data['name']
		description=event_data['short_description']
		sections=event_data['sections']
		fulldescription=""
		for section in sections:
			fulldescription+="<b>"+section['title']+"</b><br><br>"
			fulldescription+=section['text']
			fulldescription+="<br><br>"
		contacts=event_data['contacts'];
		if contacts:
			contact_name=contacts[0]['name']
			contact_phone=contacts[0]['phone']
		db.execute('DELETE FROM "main"."events" WHERE code=?',(code,))
		db.commit()
		db.execute('INSERT INTO "main"."events" \
		("code","name","description","fulldescription","type","genre","contact_name","contact_number")\
		VALUES (?,?,?,?,?,?,?,?)',\
		(code,name,description,fulldescription,type,genre,contact_name,contact_phone));	
		db.commit();
		total=total+1;
		print str(total)+" : "+event_data['name']+" Updated"



conn.request("GET", "/2015/cms/api/events")
r1 = conn.getresponse()
data = json.loads(r1.read())
categories= data[0]['sub_categories']

for category in categories:
	events= category['events']
	storeEvents("COMPETITIONS",category['name'],events)

workshops=data[1]['events']
storeEvents('WORKSHOPS','',workshops)




db.close();

