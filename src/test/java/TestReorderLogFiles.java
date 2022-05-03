import org.testng.annotations.Test;
import com.leetcode.reorderLogFiles;


public class TestReorderLogFiles {

@Test
    public void test(){
        String[] StrArray={"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        reorderLogFiles rlf = new reorderLogFiles();
        String[] res = rlf.rreorderLogFiles(StrArray);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
