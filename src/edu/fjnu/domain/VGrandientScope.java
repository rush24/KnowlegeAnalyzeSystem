package edu.fjnu.domain;

/**
 * 梯度范围关联表
 * @author vengeance
 *
 */
public class VGrandientScope {
	private int t_grandient_grandientID;//梯度ID
	private int scopes_pk_scope_ID;//范围ID
	public int getT_grandient_grandientid() {
		return t_grandient_grandientID;
	}
	public void setT_grandient_grandientid(int t_grandient_grandientid) {
		this.t_grandient_grandientID = t_grandient_grandientid;
	}
	public int getScopes_pk_scope_id() {
		return scopes_pk_scope_ID;
	}
	public void setScopes_pk_scope_id(int scopes_pk_scope_id) {
		this.scopes_pk_scope_ID = scopes_pk_scope_id;
	}
	@Override
	public String toString() {
		return "GrandientScope [t_grandient_grandientid=" + t_grandient_grandientID + ", scopes_pk_scope_id="
				+ scopes_pk_scope_ID + "]";
	}
	
}
