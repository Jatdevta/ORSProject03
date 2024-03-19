package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.LaptopDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface LaptopModelInt {


public long add(LaptopDTO dto)throws ApplicationException,DuplicateRecordException;
public void delete(LaptopDTO dto)throws ApplicationException;
public void update(LaptopDTO dto)throws ApplicationException,DuplicateRecordException;
public LaptopDTO findByPK(long pk)throws ApplicationException;
public List search(LaptopDTO dto,int pageNo, int pageSize)throws ApplicationException;

}
