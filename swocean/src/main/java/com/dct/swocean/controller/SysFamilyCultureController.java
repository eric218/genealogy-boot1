package com.dct.swocean.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dct.swocean.common.CulturePage;
import com.dct.swocean.common.FamilyIndustry;
import com.dct.swocean.entity.SysRightinfoInfo;
import com.dct.swocean.entity.SysWritingInfo;
import com.dct.swocean.service.SysFamilyCultureService;
import com.dct.swocean.util.Response;
import io.swagger.annotations.ApiOperation;


/**
 * 联谊会家族文化页面
 */
@RestController
@RequestMapping("/civilization")
@CrossOrigin(origins = "*") // 跨域
public class SysFamilyCultureController {

	@Autowired
	private SysFamilyCultureService sysFamilyCultureService;

	// 资讯前台 查询家族文化分类信息 除了家族字派
	@RequestMapping(value="/consult",method=RequestMethod.POST)
	public Response<CulturePage> consult(@RequestParam("userId") String userId,
			@RequestParam(value = "style") Integer style,
			@RequestParam(value = "areaCode") String areaCode, // areaCode 地区ID
			@RequestParam(value = "familyName") String familyName, // familyName 姓氏名称
			@RequestParam(value = "pageNow", defaultValue = "1") Integer pageNow,
			@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
			@RequestParam(value = "type", defaultValue = "05") String type,
			@RequestParam(value = "status", defaultValue = "1") Integer status) {
		 Response<CulturePage> culture = sysFamilyCultureService.culture(userId, style, pageNow, pageSize, areaCode, familyName,status,type);
		return culture;
	}

	// 进入前台家族产业发表页面显示草稿信息
	@RequestMapping(value = "/culturePublish", method = RequestMethod.POST)
	public Response<CulturePage> culturePublish(@RequestParam("userId") String userId,
			@RequestParam(value = "column", defaultValue = "2") String column,
			@RequestParam(value = "areaCode") String areaCode, // areaCode 地区ID
			@RequestParam(value = "familyName") String familyName, // familyName 姓氏名称
			@RequestParam(value = "pageNow", defaultValue = "1") Integer pageNow,
			@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
			@RequestParam(value = "type", defaultValue = "05") String type,
			@RequestParam(value = "status", defaultValue = "0") Integer status) {
		Response<CulturePage> culturePublish = sysFamilyCultureService.culturePublish(userId, column, areaCode, familyName,pageNow,pageSize,type,status);
		return culturePublish;
	}
	
	// 前台草稿详情
	@RequestMapping(value = "/detailsPublish", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> detailsPublish(@RequestParam("writingsId") String writingsId) {
		Response<SysWritingInfo> detailsPublish = sysFamilyCultureService.detailsPublish(writingsId);
		return detailsPublish;
	}
	
	// 前台草稿修改页面
	@RequestMapping(value = "/updatePublish", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> updatePublish(@RequestParam("writingsId") String writingsId) {
		Response<SysWritingInfo> detailsPublish = sysFamilyCultureService.detailsPublish(writingsId);
		return detailsPublish;
	}
	
	//前台草稿修改页面发表或者保存为草稿
	@RequestMapping(value = "/updateAnnounce", method = RequestMethod.POST)
	public Response<SysWritingInfo> announce(@RequestParam("writingsId") String writingsId,
			@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("style") Integer style, 
			@RequestParam("pic") String pic, 
			@RequestParam("synopsis") String synopsis,//简介
			@RequestParam("status") Integer status // 状态1是发表 状态0是草稿 状态2不能显示表示已删除
            ) {
		// 插入数据
		Response<SysWritingInfo> amendPublish = sysFamilyCultureService.amendPublish(writingsId, title, text, style, pic, status,synopsis);
		return amendPublish;
	}

	//前台家族文化发表页面草稿删除
	@RequestMapping(value = "/deletePublish", method = RequestMethod.POST)
	public Response<SysWritingInfo> deletePublish (@RequestParam("writingsId")String writingsId){
		Response<SysWritingInfo> details = sysFamilyCultureService.deletePublish(writingsId);
		return details;
	}
	
	// 前台页面发表数据
	@RequestMapping(value = "/publishData", method = RequestMethod.POST)
	@ApiOperation(value = "前台页面发表数据")
	public Response<SysWritingInfo> publishData(@RequestParam("userId") String userId,
			@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("synopsis") String synopsis,@RequestParam("style") Integer style, 
			@RequestParam("familyName") String familyName, // 姓氏
			@RequestParam(value = "areaCode") String areaCode, // areaCode 地区ID
			@RequestParam("pic") String pic, @RequestParam("status") Integer status,
			@RequestParam("type") Integer type) {
		// 插入数据
		Response<SysWritingInfo> publishData = sysFamilyCultureService.publishData(userId, title, text, style,synopsis,familyName,areaCode, pic, status,type);
		return publishData;
	}

	// 前台增加收藏数 除了家族字派
	@RequestMapping(value = "/collection", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> collection(@RequestParam("writingsId") String writingsId,
			@RequestParam("userId") String userId) {
		//收藏
	    Response<SysWritingInfo> response = sysFamilyCultureService.insertCollection(writingsId,userId);
		return response;
	}

	// 前台增加点赞 除了家族字派
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	@ApiOperation(value = "前台增加点赞")
	// 文章ID writingsId
	public Response<SysWritingInfo> like(@RequestParam("writingsId") String writingsId) {
			Response<SysWritingInfo> insertLike = sysFamilyCultureService.insertLike(writingsId);
			return insertLike;
	}

	// 前台查看详情增加查看数 除了家族字派
	@RequestMapping(value = "/particulars", method = RequestMethod.POST)
	@ApiOperation(value = "前台查看详情增加查看数")
	// 文章ID writingsId
	public Response<FamilyIndustry> particulars(@RequestParam("writingsId") String writingsId) {
			Response<FamilyIndustry> selectParticulars = sysFamilyCultureService.selectParticulars(writingsId);
			return selectParticulars;
	}

	
	// 家族文化后台文章分类查询
	@RequestMapping(value = "/typeQuerying", method = RequestMethod.POST)
	public Response<SysRightinfoInfo> typeQuerying(@RequestParam("parentId") Integer parentId) {
		Response<SysRightinfoInfo> addClassify = sysFamilyCultureService.typeQuerying(parentId);
		return addClassify;
	}
	
	
	// 家族文化后台文章分类添加
	@RequestMapping(value = "/addClassify", method = RequestMethod.POST)
	public Response<SysRightinfoInfo> addClassify(@RequestParam("column") String column,
			@RequestParam("rightName") String rightName) {// 文章分类名称
			Response<SysRightinfoInfo> addClassify = sysFamilyCultureService.addClassify(column, rightName);
			return addClassify;
	}

	// 家族文化后台文章分类删除 style分类的ID
	@RequestMapping(value = "/deleteClassify", method = RequestMethod.POST)
	public Response<SysRightinfoInfo> deleteClassify(@RequestParam("rightId") int rightId) {
		Response<SysRightinfoInfo> response = sysFamilyCultureService.deleteClassify(rightId);
		return response;
	}

	// **************************************************************************************************************
    //家族文化后台信息查询
	@RequestMapping(value = "/backstageCommonality", method = RequestMethod.POST)
	public Response<CulturePage> backstageCommonality(@RequestParam("userId") String userId,
			@RequestParam(value = "style") Integer style,
			@RequestParam(value = "areaCode") String areaCode, // areaCode 地区ID
			@RequestParam(value = "familyName") String familyName, // familyName 姓氏名称
			@RequestParam(value = "pageNow", defaultValue = "1") Integer pageNow,
			@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
		Response<CulturePage> backstageCommonality = sysFamilyCultureService.backstageCommonality(userId, style, pageNow, pageSize, areaCode, familyName);
		return backstageCommonality;
	}
	

	// **************************************************************************************************************

	// 家族文化后台查看详情
	@RequestMapping(value = "/backstage", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<FamilyIndustry> backstage(@RequestParam("writingsId") String writingsId) {
			Response<FamilyIndustry> backstage = sysFamilyCultureService.backstage(writingsId);
			return backstage;
	}

	// 家族文化后台添加 进入添加页面显示草稿
	@RequestMapping(value = "/addPublish", method = RequestMethod.POST)
	public Response<CulturePage> addPublish(@RequestParam("userId") String userId,
			@RequestParam(value = "column", defaultValue = "2") String column,
			@RequestParam(value = "areaCode") String areaCode, // areaCode 地区ID
			@RequestParam(value = "familyName") String familyName, // familyName 姓氏名称
			@RequestParam(value = "pageNow", defaultValue = "1") Integer pageNow,
			@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
			@RequestParam(value = "type", defaultValue = "05") String type,
			@RequestParam(value = "status", defaultValue = "0") Integer status) {
		 Response<CulturePage> culturePublish = sysFamilyCultureService.culturePublish(userId, column, areaCode, familyName,pageNow,pageSize,type,status);
		return culturePublish;
	}

	// 后台草稿详情
	@RequestMapping(value = "/offspring", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> offspring(@RequestParam("writingsId") String writingsId) {
			Response<SysWritingInfo> detailsPublish = sysFamilyCultureService.detailsPublish(writingsId);
			return detailsPublish;
	}

	// 后台草稿修改页面
	@RequestMapping(value = "/offspringPublish", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> offspringPublish(@RequestParam("writingsId") String writingsId) {
		Response<SysWritingInfo> detailsPublish = sysFamilyCultureService.detailsPublish(writingsId);
		return detailsPublish;
	}
	
	// 后台草稿修改页面发表或者保存为草稿
	@RequestMapping(value = "/updateDraft", method = RequestMethod.POST)
	public Response<SysWritingInfo> updateDraft(@RequestParam("writingsId") String writingsId,
			@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("style") Integer style, @RequestParam("pic") String pic,
			@RequestParam("synopsis") String synopsis, // 简介
			@RequestParam("status") Integer status// 状态1是发表 状态0是草稿 状态2不能显示表示已删除
     ) {
		// 插入数据
		Response<SysWritingInfo> amendPublish = sysFamilyCultureService.amendPublish(writingsId, title, text, style, pic,
				status, synopsis);
		return amendPublish;
	}
	
	//后台家族文化发表页面草稿删除
		@RequestMapping(value = "/deleteDraft", method = RequestMethod.POST)
		public Response<SysWritingInfo> deleteDraft (@RequestParam("writingsId")String writingsId){
			Response<SysWritingInfo> details = sysFamilyCultureService.deletePublish(writingsId);
			return details;
		}
	

	// 家族文化后台添加提交
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	public Response<SysWritingInfo> addData(@RequestParam("userId") String userId, @RequestParam("title") String title,
			@RequestParam("text") String text, @RequestParam("style") Integer style,
			@RequestParam("synopsis") String synopsis,@RequestParam("familyName") String familyName, // 姓氏
			@RequestParam(value = "areaCode") String areaCode, // areaCode 地区ID,
			@RequestParam("pic") String pic, @RequestParam("status") Integer status,
			@RequestParam("type") Integer type) {
		// 插入数据
		Response<SysWritingInfo> publishData = sysFamilyCultureService.publishData(userId, title, text, style, synopsis,familyName,areaCode ,pic, status,type);
		return publishData;
	}

	// 家族文化后台进入修改页面
	@RequestMapping(value = "/amend", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> amend(@RequestParam("writingsId") String writingsId) {
			Response<SysWritingInfo> amend = sysFamilyCultureService.amend(writingsId);
			return amend;
	}
	
	// 家族文化后台进入修改页面后发表或者草稿
	@RequestMapping(value = "/amendPublish", method = RequestMethod.POST)
	// 文章ID writingsId
	public Response<SysWritingInfo> amendPublish(@RequestParam("writingsId") String writingsId,
			@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("style") Integer style, @RequestParam("pic") String pic,
			@RequestParam("synopsis") String synopsis,//简介
			@RequestParam("status") Integer status) {
		// 修改数据
		Response<SysWritingInfo> amendPublish=sysFamilyCultureService.amendPublish(writingsId, title, text, style, pic, status,synopsis);
		return amendPublish;
	}
	// 家族文化后台文章数据删除
		@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
		// 文章ID writingsId
		public Response<SysWritingInfo> deleteData(@RequestParam("writingsId") String writingsId,
				@RequestParam("status") Integer status) {
			Response<SysWritingInfo> deleteData = sysFamilyCultureService.deleteData(writingsId,status);
			return deleteData;
		}
	
}
