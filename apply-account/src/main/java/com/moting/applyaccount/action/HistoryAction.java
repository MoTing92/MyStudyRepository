package com.moting.applyaccount.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moting.applyaccount.bean.History;
import com.moting.applyaccount.bean.ReturnMsg;
import com.moting.applyaccount.service.IHistoryService;
import com.moting.applyaccount.utils.Constant;
/***
 * 历史管理
 * ***/
@Controller
@RequestMapping("/history")
public class HistoryAction {

	@Resource
	private IHistoryService historyService;
	
	@RequestMapping(value="/search")
	@ResponseBody
	public ReturnMsg<History> findHistory(@RequestBody History history){
		ReturnMsg<History> re=new ReturnMsg<>();
		if(history.getUsername()!=null){
			List<History> historyList=(List<History>)historyService.findName(history.getUsername());
			re.setState(Constant.SUCCESS_STATE);
			re.setMessage("查询成功");
			re.setData(historyList);
			return re;	
		}else{
			List<History> historyList=historyService.query(history);
			re.setState(Constant.SUCCESS_STATE);
			re.setMessage("查询成功");
			re.setData(historyList);
			return re;			
		}
	}
	
}
