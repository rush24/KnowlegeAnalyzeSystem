package edu.fjnu.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.rosuda.REngine.Rserve.RserveException;

import edu.fjnu.service.RelationshipService;

/**
 * 定时器处理类，用于每隔一段时间处理更新数据
 * @author zhangzhiyong
 *
 */
public class Scheduler {

	public void sendScheduler(String dateStr){
        final Timer timer = new Timer();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date currentTime = formatter.parse(dateStr, pos);
        //计划开始运行之后每间隔一周运行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("计划开始了！");
            		updateRelationData();
            		RelationResult.clearCache();
            }
        }, currentTime, 7*24*60*60*1000);
    }
	
	public void updateRelationData(){
		RelationshipService rss = new RelationshipService();
		String[] courseNameArr = {"语文","数学","英文"};
		for(String courseName : courseNameArr){
			try {
				rss.createCrossGradesRelationCSVForRtool(courseName);
				rss.updateCsvFileForCreateMap(courseName);
//				rss.updateRImag(courseName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
