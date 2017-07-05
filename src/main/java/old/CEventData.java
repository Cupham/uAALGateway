package old;

import org.universAAL.middleware.context.ContextEvent;

public class CEventData {
	private String subject;
	private String subjectType;
	private String rdfObject;
	private String rdfPredicate;
	
	
	public CEventData() {
		super();
	}
	public CEventData(ContextEvent ce) {
		this.subject = ce.getSubjectURI();
		this.subjectType = ce.getSubjectTypeURI();
		this.rdfObject = ce.getRDFObject().toString();
		this.rdfPredicate = ce.getRDFPredicate();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getRdfObject() {
		return rdfObject;
	}
	public void setRdfObject(String rdfObject) {
		this.rdfObject = rdfObject;
	}
	public String getRdfPredicate() {
		return rdfPredicate;
	}
	public void setRdfPredicate(String rdfPredicate) {
		this.rdfPredicate = rdfPredicate;
	}
	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("   *********************\n");
		msg.append("       SUBJECT: " + this.subject + "\n");
		msg.append("       SUBJECT TYPE: " + this.subjectType + "\n");
		msg.append("       PREDICATE: " + this.rdfPredicate + "\n");
		msg.append("       OBJECT: " + this.rdfObject + "\n");
		msg.append("   *********************");
		
		return msg.toString();
	}
	
	
}
