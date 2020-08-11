package com.example.demo.java.test;

public enum EnumTest {
	FIRST("1",new First()),
	SECOND("2",new Second());
	
	private String name;
	private Interface value;
	
	private EnumTest(String name,Interface value){
		this.name=name;
		this.value=value;
	}
	
	public static Interface getTypeByName(String name) {
		Interface find = null;
        for (EnumTest type : EnumTest.values()) {
            if (type.getName().equals(name)) {
                find = type.getValue();
                break;
            }
        }
        return find;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Interface getValue() {
		return value;
	}

	public void setValue(Interface value) {
		this.value = value;
	}
	
}
