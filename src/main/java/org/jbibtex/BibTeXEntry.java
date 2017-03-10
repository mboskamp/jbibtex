/*
 * Copyright (c) 2012 University of Tartu
 * Minor changes by Miklas Boskamp 
 */
package org.jbibtex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class BibTeXEntry extends BibTeXObject {

	private Key type = null;

	private Key key = null;

	private KeyMap<Value> fields = new KeyMap<Value>();
	
	public BibTeXEntry(Key type, Key key){
		setType(type);
		setKey(key);
	}

	public BibTeXEntry getCrossReference(){
		Value value = this.fields.get(KEY_CROSSREF);

		CrossReferenceValue crossRefValue = (CrossReferenceValue)value;
		if(crossRefValue != null){
			return crossRefValue.getEntry();
		}

		return null;
	}

	public Value getField(Key key){
		Value value = this.fields.get(key);

		if(value == null){
			BibTeXEntry entry = getCrossReference();

			if(entry != null){
				return entry.getField(key);
			}
		}

		return value;
	}

	public void addField(Key key, Value value){
		this.fields.put(key, value);
	}

	public void addAllFields(Map<Key, Value> fields){
		this.fields.putAll(fields);
	}

	public void removeField(Key key){
		this.fields.remove(key);
	}

	public Key getType(){
		return this.type;
	}

	private void setType(Key type){
		this.type = type;
	}

	public Key getKey(){
		return this.key;
	}

	private void setKey(Key key){
		this.key = key;
	}

	public Map<Key, Value> getFields(){
		return Collections.unmodifiableMap(this.fields);
	}
	
	public static ArrayList<Key> getTypes(){
		return types;
	}
	
	public static ArrayList<String> getTypesAsString(){
		ArrayList<String> typesAsString = new ArrayList<String>();
		
		for (Key key : types) {
			String value = key.getValue();
			value = value.substring(0, 1).toUpperCase() + value.substring(1);
			typesAsString.add(value);
		}
		return typesAsString;
	}
	
	public static ArrayList<Key> getKeys(){
		return keys;
	}
	
	public static ArrayList<String> getKeysAsString(){
		ArrayList<String> keysAsString = new ArrayList<String>();
		
		for (Key key : keys) {
			String value = key.getValue();
			value = value.substring(0, 1).toUpperCase() + value.substring(1);
			keysAsString.add(value);
		}
		return keysAsString;
	}
 
	public Entry getEntry(){
		Entry entry = new Entry();
		Map<Key, Value> map = getFields();
		entry.setAddress(map.get(KEY_ADDRESS));
		entry.setAnnote(map.get(KEY_ANNOTE));
		entry.setAuthor(map.get(KEY_AUTHOR));
		entry.setBooktitle(map.get(KEY_BOOKTITLE));
		entry.setChapter(map.get(KEY_CHAPTER));
		entry.setCrossref(map.get(KEY_CROSSREF));
		entry.setDoi(map.get(KEY_DOI));
		entry.setEdition(map.get(KEY_EDITION));
		entry.setEditor(map.get(KEY_EDITOR));
		entry.setEprint(map.get(KEY_EPRINT));
		entry.setHowpublished(map.get(KEY_HOWPUBLISHED));
		entry.setInstitution(map.get(KEY_INSTITUTION));
		entry.setJournal(map.get(KEY_JOURNAL));
		entry.setMonth(map.get(KEY_MONTH));
		entry.setNote(map.get(KEY_NOTE));
		entry.setNumber(map.get(KEY_NUMBER));
		entry.setOrganization(map.get(KEY_ORGANIZATION));
		entry.setPages(map.get(KEY_PAGES));
		entry.setPublisher(map.get(KEY_PUBLISHER));
		entry.setSchool(map.get(KEY_SCHOOL));
		entry.setSeries(map.get(KEY_SERIES));
		entry.setTitle(map.get(KEY_TITLE));
		entry.setType(map.get(KEY_TYPE));
		entry.setUrl(map.get(KEY_URL));
		entry.setVolume(map.get(KEY_VOLUME));
		entry.setYear(map.get(KEY_YEAR));
		return entry;
	}
	
	public static final Key TYPE_ARTICLE = new Key("article");
	public static final Key TYPE_BOOK = new Key("book");
	public static final Key TYPE_BOOKLET = new Key("booklet");
	public static final Key TYPE_CONFERENCE = new Key("conference");
	public static final Key TYPE_INBOOK = new Key("inbook");
	public static final Key TYPE_INCOLLECTION = new Key("incollection");
	public static final Key TYPE_INPROCEEDINGS = new Key("inproceedings");
	public static final Key TYPE_MANUAL = new Key("manual");
	public static final Key TYPE_MASTERSTHESIS = new Key("mastersthesis");
	public static final Key TYPE_MISC = new Key("misc");
	public static final Key TYPE_PHDTHESIS = new Key("phdthesis");
	public static final Key TYPE_PROCEEDINGS = new Key("proceedings");
	public static final Key TYPE_TECHREPORT = new Key("techreport");
	public static final Key TYPE_UNPUBLISHED = new Key("unpublished");
	
	@SuppressWarnings("serial")
	public static final ArrayList<Key> types = new ArrayList<Key>() {{
	    add(TYPE_ARTICLE);
	    add(TYPE_BOOK);
	    add(TYPE_BOOKLET);
	    add(TYPE_CONFERENCE);
	    add(TYPE_INBOOK);
	    add(TYPE_INCOLLECTION);
	    add(TYPE_INPROCEEDINGS);
	    add(TYPE_MANUAL);
	    add(TYPE_MASTERSTHESIS);
	    add(TYPE_MISC);
	    add(TYPE_PHDTHESIS);
	    add(TYPE_PROCEEDINGS);
	    add(TYPE_TECHREPORT);
	    add(TYPE_UNPUBLISHED);
	}};

	public static final Key KEY_ADDRESS = new Key("Adresse");
	public static final Key KEY_ANNOTE = new Key("Annotation");
	public static final Key KEY_AUTHOR = new Key("Autor");
	public static final Key KEY_BOOKTITLE = new Key("Buchtitel");
	public static final Key KEY_CHAPTER = new Key("Kapitel");
	public static final Key KEY_CROSSREF = new Key("Referenz");
	public static final Key KEY_DOI = new Key("doi");
	public static final Key KEY_EDITION = new Key("Ausgabe");
	public static final Key KEY_EDITOR = new Key("Redakteur");
	public static final Key KEY_EPRINT = new Key("eprint");
	public static final Key KEY_HOWPUBLISHED = new Key("Veröffentlicht als");
	public static final Key KEY_INSTITUTION = new Key("Institution");
	public static final Key KEY_JOURNAL = new Key("Zeitschrift");
	public static final Key KEY_KEY = new Key("Schlüssel");
	public static final Key KEY_MONTH = new Key("Monat");
	public static final Key KEY_NOTE = new Key("Notiz");
	public static final Key KEY_NUMBER = new Key("Nummer");
	public static final Key KEY_ORGANIZATION = new Key("Organisation");
	public static final Key KEY_PAGES = new Key("Seiten");
	public static final Key KEY_PUBLISHER = new Key("Herausgeber");
	public static final Key KEY_SCHOOL = new Key("Schule");
	public static final Key KEY_SERIES = new Key("Reihe");
	public static final Key KEY_TITLE = new Key("Titel");
	public static final Key KEY_TYPE = new Key("Typ");
	public static final Key KEY_URL = new Key("url");
	public static final Key KEY_VOLUME = new Key("Band");
	public static final Key KEY_YEAR = new Key("Jahr");
	
	@SuppressWarnings("serial")
	public static final ArrayList<Key> keys = new ArrayList<Key>() {{
	    add(KEY_ADDRESS);
	    add(KEY_ANNOTE);
	    add(KEY_AUTHOR);
	    add(KEY_BOOKTITLE);
	    add(KEY_CHAPTER);
	    add(KEY_CROSSREF);
	    add(KEY_DOI);
	    add(KEY_EDITION);
	    add(KEY_EDITOR);
	    add(KEY_EPRINT);
	    add(KEY_HOWPUBLISHED);
	    add(KEY_INSTITUTION);
	    add(KEY_JOURNAL);
	    add(KEY_MONTH);
	    add(KEY_NOTE);
	    add(KEY_NUMBER);
	    add(KEY_ORGANIZATION);
	    add(KEY_PAGES);
	    add(KEY_PUBLISHER);
	    add(KEY_SCHOOL);
	    add(KEY_SERIES);
	    add(KEY_TITLE);
	    add(KEY_TYPE);
	    add(KEY_URL);
	    add(KEY_VOLUME);
	    add(KEY_YEAR);
	}};
}