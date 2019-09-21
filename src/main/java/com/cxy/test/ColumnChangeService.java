package com.cxy.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lidongpeng on 2018/3/28.
 */
public class ColumnChangeService {

    private static final Log LOGGER = LogFactory.getLog(ColumnChangeService.class);

    private static volatile ColumnChangeService columnChangeService=null;

    private static  Map<String,String> map=new ConcurrentHashMap<String, String>();
    static AtomicLong atomicLong=new AtomicLong(0);
    private ColumnChangeService(){

    }
    public  static ColumnChangeService getColumnChangeService(String dataId,String group,String diamondKey){
        ColumnChangeService result = columnChangeService;
        if (result==null){
            synchronized (ColumnChangeService.class){
                result=columnChangeService;
                if (result==null){
                    if (StringUtils.isEmpty(dataId) || StringUtils.isEmpty(group) || StringUtils.isEmpty(diamondKey)){
                        LOGGER.error("param is null");
                        return null;
                    }
                    String allConfigStr="Person-attachAddress:PersonA-attachAddress1|Person-sex:PersonA-sex1|Person-name:PersonB-name2|Person-height:PersonB-height2|Person-name:PersonC-name2";
                    System.out.println(atomicLong.incrementAndGet());
                    String[] array=allConfigStr.split("\\|");
                    for (String elements:array) {
                        String[] temp=elements.split(":");
                        map.put(temp[0],temp[1]);
                        map.put(temp[1],temp[0]);
                    }
                    columnChangeService=new ColumnChangeService();
                }
            }
        }
        return columnChangeService;
    }

    public String getObserveColumnName(String table,String oldColumn){
        final String key=new StringBuilder(table).append("-").append(oldColumn).toString();
        String newTableColumn=map.get(key);
        if (newTableColumn==null || newTableColumn.length()==0){
            LOGGER.error("table="+table+"oldColumn="+oldColumn+"has not config value");
            return null;
        }else {
            String[] res=newTableColumn.split("-");
            if (res.length<2){
                LOGGER.error("table="+table+"oldColumn="+oldColumn+" configure illegal ");
                return null;
            }
            return res[1];
        }
    }
    public Object revertObject(Object object){
        Class<?> c= object.getClass();
        return null;
    }
}
