package org.jbibtex;

public class Entry {
	private Value address;
	private Value annote;
	private Value author;
	private Value booktitle;
	private Value chapter;
	private Value crossref;
	private Value doi;
	private Value edition;
	private Value editor;
	private Value eprint;
	private Value howpublished;
	private Value institution;
	private Value journal;
	private Value month;
	private Value note;
	private Value number;
	private Value organization;
	private Value pages;
	private Value publisher;
	private Value school;
	private Value series;
	private Value title;
	private Value type;
	private Value url;
	private Value volume;
	private Value year;

	public String getAddress() {
		return getAsStringOrNull(address);
	}

	public void setAddress(Value address) {
		this.address = address;
	}

	public String getAnnote() {
		return getAsStringOrNull(annote);
	}

	public void setAnnote(Value annote) {
		this.annote = annote;
	}

	public String getAuthor() {
		return getAsStringOrNull(author);
	}

	public void setAuthor(Value author) {
		this.author = author;
	}

	public String getBooktitle() {
		return getAsStringOrNull(booktitle);
	}

	public void setBooktitle(Value booktitle) {
		this.booktitle = booktitle;
	}

	public String getChapter() {
		return getAsStringOrNull(chapter);
	}

	public void setChapter(Value chapter) {
		this.chapter = chapter;
	}

	public String getCrossref() {
		return getAsStringOrNull(crossref);
	}

	public void setCrossref(Value crossref) {
		this.crossref = crossref;
	}

	public String getDoi() {
		return getAsStringOrNull(doi);
	}

	public void setDoi(Value doi) {
		this.doi = doi;
	}

	public String getEdition() {
		return getAsStringOrNull(edition);
	}

	public void setEdition(Value edition) {
		this.edition = edition;
	}

	public String getEditor() {
		return getAsStringOrNull(editor);
	}

	public void setEditor(Value editor) {
		this.editor = editor;
	}

	public String getEprint() {
		return getAsStringOrNull(eprint);
	}

	public void setEprint(Value eprint) {
		this.eprint = eprint;
	}

	public String getHowpublished() {
		return getAsStringOrNull(howpublished);
	}

	public void setHowpublished(Value howpublished) {
		this.howpublished = howpublished;
	}

	public String getInstitution() {
		return getAsStringOrNull(institution);
	}

	public void setInstitution(Value institution) {
		this.institution = institution;
	}

	public String getJournal() {
		return getAsStringOrNull(journal);
	}

	public void setJournal(Value journal) {
		this.journal = journal;
	}

	public String getMonth() {
		return getAsStringOrNull(month);
	}

	public void setMonth(Value month) {
		this.month = month;
	}

	public String getNote() {
		return getAsStringOrNull(note);
	}

	public void setNote(Value note) {
		this.note = note;
	}

	public String getNumber() {
		return getAsStringOrNull(number);
	}

	public void setNumber(Value number) {
		this.number = number;
	}

	public String getOrganization() {
		return getAsStringOrNull(organization);
	}

	public void setOrganization(Value organization) {
		this.organization = organization;
	}

	public String getPages() {
		return getAsStringOrNull(pages);
	}

	public void setPages(Value pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return getAsStringOrNull(publisher);
	}

	public void setPublisher(Value publisher) {
		this.publisher = publisher;
	}

	public String getSchool() {
		return getAsStringOrNull(school);
	}

	public void setSchool(Value school) {
		this.school = school;
	}

	public String getSeries() {
		return getAsStringOrNull(series);
	}

	public void setSeries(Value series) {
		this.series = series;
	}

	public String getTitle() {
		return getAsStringOrNull(title);
	}

	public void setTitle(Value title) {
		this.title = title;
	}

	public String getType() {
		return getAsStringOrNull(type);
	}

	public void setType(Value type) {
		this.type = type;
	}

	public String getUrl() {
		return getAsStringOrNull(url);
	}

	public void setUrl(Value url) {
		this.url = url;
	}

	public String getVolume() {
		return getAsStringOrNull(volume);
	}

	public void setVolume(Value volume) {
		this.volume = volume;
	}

	public String getYear() {
		return getAsStringOrNull(year);
	}

	public void setYear(Value year) {
		this.year = year;
	}

	private String getAsStringOrNull(Value value) {
		return value == null ? null : value.toUserString();
	}

	public Value[] getValues() {
		Value[] values = { address, annote, author, booktitle, chapter, crossref, doi, edition, editor, eprint,
				howpublished, institution, journal, month, note, number, organization, pages, publisher, school, series,
				title, type, url, volume, year };
		return values;
	}
}