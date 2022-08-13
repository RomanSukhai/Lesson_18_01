package ua.lviv.lgs.dao.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jakarta.persistence.EntityManager;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.mappers.BucketMapper;
import ua.lviv.lgs.shared.FactoryManagerd;
import ua.lviv.lgs.untils.ConectorUtils;

public class BucketDaoImpl implements BucketDao{
	private final static String READ_BY_ALL 			= "SELECT * FROM bucket";
	private final static String READ_BY_ALLID 			= "SELECT product_id FROM bucket";
	private final static String CREATE_BY				= "INSERT INTO bucket(user_id,product_id,purchase_date) VALUES (?,?,?)";
	private final static String READ_BY_ID 				= "SELECT * FROM bucket WHERE id = ?";
	private final static String UPDATE_BY_ID 			= "UPDATE bucket SET user_id=?,product_id=?,purchase_date=? WHERE id = ?";
	private final static String DELETE_BY_ID 			= "DELETE FROM bucket WHERE  id = ?";
	private final static String READ_BY_PRODUCT_ID		= "SELECT product_id FROM bucket";
	private final static String READ_BY_USER_ID 		= "SELECT user_id FROM bucket";
	private final static String READ_BY_PURCHASE_DATE 	= "SELECT purchase_date FROM bucket";
	private final static String DROP_TABLE 				= "DROP TABLE IF EXISTS bucket";
	private final static Logger LOGER 					= Logger.getLogger(BucketDaoImpl.class);
	
	
	private Connection connection;
	private PreparedStatement preparedStatement ;
	private EntityManager em = (EntityManager) FactoryManagerd.getEntityManager(); 
	


	public BucketDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.connection =ConectorUtils.connection();
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGER.error(e);
		}
		
	}

	@Override
	public void dropTableIfExists(){
		try {
			preparedStatement = connection.prepareStatement(DROP_TABLE);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGER.error(e);
		}
		
	}
	@Override
	public Bucket create(Bucket bucket) {
		try {
			preparedStatement = connection.prepareStatement(CREATE_BY,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,bucket.getUser_id());
			preparedStatement.setInt(2,bucket.getProduct_id());
			preparedStatement.setDate(3,(java.sql.Date) new Date(bucket.getPurchase_date().getTime()));
			em.getTransaction().begin();
			em.persist(bucket);
			em.getTransaction().commit();
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) { 
			LOGER.error(e);
		}
		return bucket;
		
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Integer bucketid = resultSet.getInt("id");
			Integer user_id = resultSet.getInt("user_id");
			Integer product_id = resultSet.getInt("product_id");
			java.sql.Date purchese_date =  resultSet.getDate("purchese_date");
			bucket = new Bucket(bucketid,user_id,product_id,purchese_date);
		} catch (SQLException e) {
			LOGER.error(e);
		}return bucket;	
		
	}

	@Override
	public Bucket update(Bucket bucket, Integer id)  {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setInt(1,bucket.getUser_id());
			preparedStatement.setInt(2,bucket.getProduct_id());
			preparedStatement.setDate(3,bucket.getPurchase_date());
			preparedStatement.setInt(4,id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			LOGER.error(e);
		}
		return bucket;
		
	}

	@Override
	public void readByAll()  {
		try {
			preparedStatement = connection.prepareStatement(READ_BY_PRODUCT_ID);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			LOGER.error(e);
		}
		
	}

	@Override
	public void readByUser_id() {
		try {
			preparedStatement = connection.prepareStatement(READ_BY_USER_ID);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			LOGER.error(e);
		}
		
	}

	@Override
	public void readByProduct_id()  {
		try {
			preparedStatement = connection.prepareStatement(READ_BY_PRODUCT_ID);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			LOGER.error(e);
		}
		
	}

	@Override
	public void readByPurchase_date() {
		try {
			preparedStatement = connection.prepareStatement(READ_BY_PURCHASE_DATE);
			preparedStatement.executeQuery();
		}
		catch (Exception e) {
			LOGER.error(e);
		}
	}

	@Override
	public List<Bucket> readAll(){
			List<Bucket> bucket = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				bucket.add(BucketMapper.map(resultSet));
			}
		} 
		catch (Exception e) {
			LOGER.error(e);
		}
			return bucket;
	}


}
