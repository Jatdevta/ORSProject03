package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.LaptopDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class LaptopModelHibImp implements LaptopModelInt {

	@Override
	public long add(LaptopDTO dto) throws ApplicationException, DuplicateRecordException {
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

	@Override
	public void delete(LaptopDTO dto) throws ApplicationException {
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

	@Override
	public void update(LaptopDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
		// LaptopDTO existDto = findByLogin(dto.getLogin());
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

	@Override
	public LaptopDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		LaptopDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (LaptopDTO) session.get(LaptopDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List search(LaptopDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		ArrayList<LaptopDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(LaptopDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getCustomerName() != null && dto.getCustomerName().length() > 0) {
					criteria.add(Restrictions.like("CustomerName", dto.getCustomerName() + "%"));

					if (dto.getPrice() != null && dto.getPrice().length() > 0) {
						criteria.add(Restrictions.like("Price", dto.getPrice() + "%"));
					}
				}
				if (dto.getQuantity() != null && dto.getQuantity().length() > 0) {
					criteria.add(Restrictions.like("Quantity", dto.getQuantity() + "%"));
				}
				if (dto.getOrderStatus() != null && dto.getOrderStatus().length() > 0) {
					criteria.add(Restrictions.like("OrderStatus", dto.getOrderStatus() + "%"));
				}

			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<LaptopDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}

}
