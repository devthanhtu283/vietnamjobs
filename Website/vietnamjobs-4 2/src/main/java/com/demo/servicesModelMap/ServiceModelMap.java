package com.demo.servicesModelMap;


import com.demo.entities.Category;
import com.demo.entities.Employer;
import com.demo.entities.Experience;
import com.demo.entities.Local;
import com.demo.entities.Rank;
import com.demo.entities.Type;
import com.demo.entities.TypeAccount;
import com.demo.entities.Wage;


public interface ServiceModelMap {
	
	public Employer findbyemployername(String name);
	
	public Category findbycategoryname(String name);
	
	public TypeAccount findbytypeaccountname(String name);
	
	public Experience findbyexpname(String name);
	
	public Local findbylocalname(String name);
	
	public Rank findbyrankname(String name);
	
	public Type findbytypename(String name);
	
	public Wage findbywagename(String name);
}
