package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.OrderDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

/**
 * Hibernate implements of User model
 * 
 * @author Raj Jat
 *
 */
public class OrderModelHibImpl implements OrderModelInt {

	public long add(OrderDTO dto) throws ApplicationException, DuplicateRecordException {

		System.out.println("in addddddddddddd");
		// TODO Auto-generated method stub
		/* log.debug("usermodel hib start"); */

		// BankDTO existDto = null;
		// existDto = findByLogin(dto.getLogin());
		// if (existDto != null) {
		// throw new DuplicateRecordException("login id already exist");
		// }
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);
			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in User Add " + e.getMessage());
		} finally {
			session.close();
		}
		/* log.debug("Model add End"); */
		return dto.getId();

	}

	public void delete(OrderDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(OrderDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		// BankDTO existDto = findByLogin(dto.getLogin());
		// Check if updated LoginId already exist
		// if (existDto != null && existDto.getId() != dto.getId()) {
		// throw new DuplicateRecordException("LoginId is already exist");
		// }

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public OrderDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		OrderDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (OrderDTO) session.get(OrderDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	

		
	

	public List search(OrderDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

//		System.out.println(
//				"hellllo" + pageNo + "....." + pageSize + "........" + dto.getId() + "......" + dto.getRoleId());

		Session session = null;
		ArrayList<OrderDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(OrderDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getPayment() != null && dto.getPayment().length() > 0) {
					criteria.add(Restrictions.like("accountNo", dto.getPayment() + "%"));
				}
				if (dto.getReciept() != null && dto.getReciept().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getReciept() + "%"));
				}

			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<OrderDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}

	
	}


