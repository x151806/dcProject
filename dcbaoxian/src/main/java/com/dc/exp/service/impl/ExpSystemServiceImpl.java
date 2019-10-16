package com.dc.exp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CreatIDUtil;
import com.dc.common.vo.PageObject;
import com.dc.common.vo.PicUploadResult;
import com.dc.exp.dao.ExpMissionDao;
import com.dc.exp.dao.ExpSystemDao;
import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpSystem;
import com.dc.exp.entity.ExpSystemUpload;
import com.dc.exp.service.ExpSystemService;
import com.dc.sys.vo.AllCommon;
import com.dc.sys.vo.SysUserDeptVo;

@Service
public class ExpSystemServiceImpl  implements ExpSystemService{

	@Autowired
	private ExpSystemDao expSystemDao;
	@Autowired
	private ExpMissionDao expMissionDao;

	private  String aId = "0";

	//该数据从spring容器中动态获取数据
	@Value("${image.localpath}")
	private String localPath;  //"C:/zmi/file/dc-upload/system-problem/";





	@Override
	public int insertObject(ExpSystem entity) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getProblem()))
			throw new IllegalArgumentException("问题内容不能为空");
		//.......

		CreatIDUtil uid = new CreatIDUtil();
		String repId = uid.getNextId();
		aId = repId;
		String aa="";
		Integer status=1;
		entity.setProblemId(repId);
		entity.setStatus(status);
		entity.setRemarkContent(aa);
		entity.setRemarkSatrap(aa);
		//2.将数据持久化到数据库
		int rows=expSystemDao.insertSystem(entity);
		//3.返回结果
		return rows;
	}


	@Override
	public int insertProgressObject(ExpProgress entity) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		CreatIDUtil uid = new CreatIDUtil();
		String repId = uid.getNextId();
		entity.setProgressId(repId);
		int rows=expSystemDao.insertProgress(entity);

		return rows;
	}

	@Override
	public List<ExpProgress> findProgressObject(String disId) {

		List<ExpProgress> list = expSystemDao.findProgressById(disId);
		if(list.size()<1)
			throw new IllegalArgumentException("没有对应记录");
		return list;
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
	public PicUploadResult upload(MultipartFile[] uploadFile,ExpSystemUpload entity) {
		PicUploadResult result = new PicUploadResult();
		StringBuffer sb = new StringBuffer();

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

				if(entity.getDisId()== null) {
					entity.setDisId(aId);
				}

				entity.setUploadId(uploadId);
				entity.setSite(localPathReal);
				entity.setFileName(fileName);

				expSystemDao.updateAvatar(entity);



				//定义url
				//String url = "https://img14.360buyimg.com/n0/jfs/t1/7301/36/10557/363153/5c231de0E0a5565dd/2e8054392374dc29.jpg";
			}
			String url = sb.deleteCharAt(sb.length() - 1).toString();
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
		String dateDir = 
				new SimpleDateFormat("yyyy/MM/dd")
				.format(new Date());
		//  E:/dc-upload/system-problem/2019/01/29
		String localPathDir = localPath + dateDir;
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
		String localPathReal = localPathDir + "/" + realName;
		return localPathReal;
	}













































	@Override
	public int deleteObjects(String... ids) {
		//1.参数合法性校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("请先选择");
		//2.执行删除操作
		int rows=-1;
		try{
			rows=expSystemDao.deleteSystem(ids);
		}catch(Throwable e){
			e.printStackTrace();
			//给运维发短信
			throw new RuntimeException("服务故障，恢复中");
		}
		//3.对删除结果进行校验
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");

		String[] num = ids;
		for (int i = 0; i < num.length; i++) {
			List<ExpSystemUpload> list= expSystemDao.findUploadByDid(num[i]);

			for (ExpSystemUpload exp : list) {
				System.out.println();
				expSystemDao.deleteUploadByDid(exp.getDisId());
				File file = new File(exp.getSite());
				file.delete();
				//				boolean isDelete = file.delete();

				//				if(isDelete==true) {
				//					succeed.append(exp.getFileName());
				//					succeed.append(" ");
				//				}else {
				//					defeated.append(exp.getFileName());
				//					defeated.append(" ");
				//				}
			}
		}


		//4.返回结果
		return rows;
	}



	@Override
	public int deleteByUploadId(String uploadId,String site) {
		int rows=expSystemDao.deleteUpload(uploadId);

		if(rows==0)
			throw new ServiceException("记录可能已经不存在");

		File file = new File(site);
		file.delete();
		//			boolean isDelete = file.delete();

		//			if(isDelete==true) {
		//				succeed.append(exp.getFileName());
		//				succeed.append(" ");
		//			}else {
		//				defeated.append(exp.getFileName());
		//				defeated.append(" ");
		//			}

		return 0;
	}



















	@Override
	public PageObject<ExpSystem> findPageSystem(String username,Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=expSystemDao.getRowCount(username);
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=15;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ExpSystem> records=
				expSystemDao.findPageSystem(username,startIndex,pageSize);
		//5.封装查询结果。
		PageObject<ExpSystem> po=new PageObject<ExpSystem>();
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














	@Override
	public List<SysUserDeptVo> findUserNames() {
		return expSystemDao.findUserName();
	}














	@Override
	public PageObject<ExpSystem> findPageAllocating(String username, Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=expSystemDao.getAllocatingCount(username);
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=15;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ExpSystem> records=
				expSystemDao.findPageAllocating(username,startIndex,pageSize);
		//5.封装查询结果。
		PageObject<ExpSystem> po=new PageObject<ExpSystem>();
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














	@Override
	public int doUpdateObjects(String ids,ExpSystem entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("对象不能为空");



		//2.更新用户自身信息
		int rows=0;
		String[] num = ids.split(",");
		for (int i = 0; i < num.length; i++) {
			if(entity.getStatus()!=null) {
				ExpSystem pro = expSystemDao.findByDid(num[i]);
				if(StringUtils.isEmpty(pro.getExecutor()))
					throw new IllegalArgumentException("请先分配执行人！");
			}

			entity.setProblemId(num[i]);
			int row=expSystemDao.updateSystem(entity);
			rows=rows+row;
		}

		//3.保存用户与角色关系数据

		//4.返回结果
		return rows;
	}




	@Override
	public int doUpdateStatus(ExpSystem entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("对象不能为空");
		if(StringUtils.isEmpty(entity.getStatus()))
			throw new IllegalArgumentException("阶段不能为空");

		Date date = null;
		if(entity.getStatus()==7) {
			date = new Date();
			entity.setFinishTime(date);
		}else {
			expSystemDao.updateFinishTime(entity.getProblemId());
		}

		//2.更新用户自身信息
		int row=expSystemDao.updateSystem(entity);

		//3.保存用户与角色关系数据

		//4.返回结果
		return row;
	}



	/**
	 * 点评已经不用了
	 */
	@Override
	public int doUpdateRemark(ExpSystem entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("对象不能为空");
		if(StringUtils.isEmpty(entity.getRemarkContent()))
			throw new IllegalArgumentException("点评内容不能为空");

		CreatIDUtil uid = new CreatIDUtil();
		String details = entity.getRemarkContent();;
		Integer type = 2;
		int sum = expMissionDao.findCommonComb(type,details);
		if(sum==0) {
			AllCommon ent = new AllCommon();
			String cid = uid.getNextId();
			ent.setId(cid);
			ent.setDetails(details);
			ent.setType(2);
			expMissionDao.insertCommon(ent);
		}



		//2.更新用户自身信息
		int row=expSystemDao.updateSystem(entity);

		//3.保存用户与角色关系数据

		//4.返回结果
		return row;
	}








	@Override
	public List<ExpSystemUpload> findSystemObjects(String disId) {

		List<ExpSystemUpload> list = expSystemDao.findPageUpload(disId);

		if(list.size()==0)
			throw new ServiceException("没有对应记录");
		return list;
	}


	@Override
	public ExpSystem findObjectsByDid(String problemId) {
		return expSystemDao.findByDid(problemId);
	}


	@Override
	public int doUpdateAll(ExpSystem entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("对象不能为空");
		if(StringUtils.isEmpty(entity.getProblem()))
			throw new IllegalArgumentException("问题内容不能为空");
		int rows =expSystemDao.updateSystem(entity); 
		//4.返回结果
		return rows;
	}






























































}
