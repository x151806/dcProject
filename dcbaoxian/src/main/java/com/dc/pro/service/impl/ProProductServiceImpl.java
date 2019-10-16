package com.dc.pro.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CreatIDUtil;
import com.dc.common.vo.PageObject;
import com.dc.common.vo.PicUploadResult;
import com.dc.pro.dao.ProProductDao;
import com.dc.pro.entity.ProProduct;
import com.dc.pro.entity.ProProductUpload;
import com.dc.pro.service.ProProductService;
@Service
public class ProProductServiceImpl implements ProProductService{

	@Autowired
	private ProProductDao proProductDao;

	private  String aId = "";
	private  String planId = "";

	//该数据从spring容器中动态获取数据
	@Value("${product.localpath}")
	private String localPath;  //"C:/zmi/file/dc-upload/product-plan/"








	@Override
	public int saveObject(ProProduct entity) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getInsurance()))
			throw new IllegalArgumentException("险种名称不能为空");

		Date date = new Date();
		CreatIDUtil uid = new CreatIDUtil();
		String repId = uid.getNextId();
		aId = repId;
		entity.setProductId(repId);
		entity.setInsurancePlan(aId);
		entity.setStatus("上架");
		entity.setPutawayTime(date);
		entity.setValid(1);
		entity.setRelation(repId);

		//2.将数据持久化到数据库
		int rows=proProductDao.insertObject(entity);
		//3.返回结果
		return rows;
	}

	@Override
	public int updateProduct(ProProduct entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getInsurance()))
			throw new IllegalArgumentException("险种名称不能为空");

		Date a = new Date();
		entity.setUpdateTime(a);

		//3.保存用户与角色关系数据
		int rows =proProductDao.updateObject(entity); 
		//4.返回结果
		return rows;
	}

	@Override
	public PageObject<ProProduct> findPageObjects(String username, String insurance, String status,String insuranceType,String classify,String cooperator,
			Date updateTime, Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=proProductDao.getRowCount(username, insurance, status, insuranceType, classify, cooperator, updateTime);
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=100;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ProProduct> records=
				proProductDao.findPageObjects(username, insurance, status, insuranceType, classify, cooperator, updateTime,startIndex,pageSize);
		//5.封装查询结果。
		PageObject<ProProduct> po=new PageObject<ProProduct>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		/*int pageCount=rowCount/pageSize;
								if(rowCount%pageSize!=0){
									pageCount++;
								}*/
		po.setPageCount((rowCount-1)/pageSize+1);

		//6.返回结果
		return po;
	}




	/**
	 * 文件上传实现思路
	 * 1.校验文件类型 jpg|png|gif....
	 * 2.校验是否为恶意程序
	 * 3.为了防止图片检索速度慢,采用分文件存储  yyyy/MM/dd/
	 * 4.防止文件重名  UUID + 随机数(3)
	 * 5.实现文件上传 	  
	 */
	@Override
	public PicUploadResult upload(MultipartFile[] uploadFile, ProProductUpload entity) {
		PicUploadResult result = new PicUploadResult();
		StringBuffer sb = new StringBuffer();
		StringBuffer sid = new StringBuffer();

		try {
			for(int i=0;i<uploadFile.length;i++) {
				String localPathReal=upMethod(uploadFile[i]);


				//实现文件上传
				uploadFile[i].transferTo(new File(localPathReal));
				String fileName = uploadFile[i].getOriginalFilename();
				sb.append(fileName).append(",");
				//写入数据库
				CreatIDUtil uid = new CreatIDUtil();
				String uploadId = uid.getNextId();

				sid.append(uploadId).append(",");

				entity.setUploadId(uploadId);
				entity.setSite(localPathReal);
				entity.setFileName(fileName);

				proProductDao.insertUpload(entity);


				//定义url
				//String url = "https://img14.360buyimg.com/n0/jfs/t1/7301/36/10557/363153/5c231de0E0a5565dd/2e8054392374dc29.jpg";
			}
			String url = sb.deleteCharAt(sb.length() - 1).toString();
			String ids = sid.deleteCharAt(sid.length() - 1).toString();

			ProProduct entityss = new ProProduct();

			entityss.setProductId(aId);
			entityss.setInsurancePlan(ids);

			proProductDao.updateObject(entityss);


			result.setUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);	//文件上传失败
			return result;
		}



		//将字符全部小写

		//fileName = fileName.toLowerCase();
		//if(!fileName.matches("^.+\\.(jpg|png|gif)$")) { result.setError(1); //不是图片
		//return result; }



		return result;
	}
































	public String upMethod(MultipartFile uploadFile) {
		//1.获取文件名称  
		String fileName = uploadFile.getOriginalFilename();
		String nameOne = fileName;

		//3.实现分文件存储
		//		String dateDir = 
		//				new SimpleDateFormat("yyyy/MM/dd")
		//				.format(new Date());
		//  E:/dc-upload/system-problem/2019/01/29
		String localPathDir = localPath;
		//判断文件夹是否存在
		File fileDir = new File(localPathDir);
		if(!fileDir.exists()) {
			fileDir.mkdirs();	//创建文件夹
		}

		//4.定义文件名称
		long milis = System.currentTimeMillis();
		String a  =String.valueOf(milis); 
		//		String num = a.substring(a.length()-8,a.length());
		//		String fileType = 
		//				fileName.substring
		//				(fileName.lastIndexOf("."));
		//形成文件名称
		String realName = a + "-" +nameOne;

		//E:/jt-upload/2019/01/29/abc.jpg
		String localPathReal = localPathDir + realName;
		return localPathReal;
	}

	@Override
	public ProProduct findObjectById(String productId) {
		return proProductDao.findObjectById(productId);
	}

	@Override
	public List<ProProductUpload> findUploads(String insurancePlan) {
		return proProductDao.findUpload(insurancePlan);
	}

	@Override
	public int updateOut(ProProduct entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("修改对象不能为空");


		Date a = new Date();
		entity.setSoldOutTime(a);
		entity.setStatus("下架");

		//3.保存用户与角色关系数据

		int rows =proProductDao.updateObject(entity); 
		//4.返回结果
		return rows;
	}























	@Override
	public int saveUpObject(ProProduct entity,String upId) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getInsurance()))
			throw new IllegalArgumentException("险种名称不能为空");

		Date date = new Date();
		CreatIDUtil uid = new CreatIDUtil();
		String repId = uid.getNextId();
		aId = repId;
		entity.setProductId(repId);
		
		entity.setUpdateTime(date);
		entity.setValid(1);
		
		planId=entity.getInsurancePlan();

		//2.将数据持久化到数据库
		int rows=proProductDao.insertObject(entity);
		if(rows==1) {
			ProProduct enti = new ProProduct();
			enti.setProductId(upId);
			enti.setValid(0);
			enti.setValidTime(date);

			proProductDao.updateObject(enti);
			
		}
		//3.返回结果
		return rows;
	}

	@Override
	public PicUploadResult uploadUpdate(MultipartFile[] uploadFile, ProProductUpload entity) {
		PicUploadResult result = new PicUploadResult();
		StringBuffer sb = new StringBuffer();
		StringBuffer sid = new StringBuffer();

		try {
			for(int i=0;i<uploadFile.length;i++) {
				String localPathReal=upMethod(uploadFile[i]);


				//实现文件上传
				uploadFile[i].transferTo(new File(localPathReal));
				String fileName = uploadFile[i].getOriginalFilename();
				sb.append(fileName).append(",");
				//写入数据库
				CreatIDUtil uid = new CreatIDUtil();
				String uploadId = uid.getNextId();

				sid.append(uploadId).append(",");

				entity.setUploadId(uploadId);
				entity.setSite(localPathReal);
				entity.setFileName(fileName);

				proProductDao.insertUpload(entity);


				//定义url
				//String url = "https://img14.360buyimg.com/n0/jfs/t1/7301/36/10557/363153/5c231de0E0a5565dd/2e8054392374dc29.jpg";
			}
			String url = sb.deleteCharAt(sb.length() - 1).toString();
			String ids = sid.deleteCharAt(sid.length() - 1).toString();
			String aplan = planId+","+ids;
			ProProduct entityss = new ProProduct();

			entityss.setProductId(aId);
			entityss.setInsurancePlan(aplan);

			proProductDao.updateObject(entityss);


			result.setUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);	//文件上传失败
			return result;
		}



		//将字符全部小写

		//fileName = fileName.toLowerCase();
		//if(!fileName.matches("^.+\\.(jpg|png|gif)$")) { result.setError(1); //不是图片
		//return result; }



		return result;
	}

	@Override
	public List<ProProduct> findOldObjects(String relation, String productId) {
		return proProductDao.findOldObjects(relation,productId);
	}



























}
