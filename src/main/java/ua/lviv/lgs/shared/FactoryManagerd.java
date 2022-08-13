package ua.lviv.lgs.shared;

import javax.persistence.*;

public class FactoryManagerd {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManage;
	
	public static EntityManagerFactory getentityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("IShopPersistence");
				
		}
		return entityManagerFactory;
	}
	
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManage =  entityManagerFactory.createEntityManager();
		}
		return entityManage;
	}
	

	
}
