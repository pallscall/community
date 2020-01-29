package blog.blogdemo.Dto;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.math.*;
@Data
public class PageDto {
    public List<QuestionDto> questions;

    private boolean showPre;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private int curPage;
    private int totalNum;
    private List<Integer>pages = new ArrayList<>();

    public void setPageInfo(int totalQuestion, int page, int size) {
        totalNum = (int) Math.ceil((double)totalQuestion/size);
//        totalNum = Math.min(totalNum,1);
        page = Math.max(page,1);
        page = Math.min(page,totalNum);
        curPage = page;
        pages.add(page);
        for(int i = 1; i <= 3; i++){
            if(page+i<=totalNum){
                pages.add(page+i);
            }
            if(page-i>=1){
                pages.add(0,page-i);
            }
        }
        //是否展示前一页
        if(page == 1) showPre = false;
        else showPre = true;

        //是否展示后一页
        if(page == totalNum) showNext = false;
        else showNext = true;

        //是否包含第一页
        if(pages.contains(1)) showFirstPage=false;
        else showFirstPage=true;

        //是否包含最后一页
        if(pages.contains(totalNum)) showEndPage=false;
        else showEndPage = true;

    }
}
