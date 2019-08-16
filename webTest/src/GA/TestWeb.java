package GA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.DocumentationTool.Location;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * Servlet implementation class TestWeb
 */
@WebServlet("/TestWeb")
public class TestWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static List<String> locations= new ArrayList<String>();
    
    public static String[] keyInit;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
			System.out.println("地点信息初始化开始...");
			request.setCharacterEncoding("UTF-8");
	        Enumeration<String> names = request.getParameterNames();
	        while (names.hasMoreElements()){
	            String name = (String)names.nextElement(); // 得到名字
	            String[] values = request.getParameterValues(name);
	            for (int i=0; values!=null&&i<values.length; i++){
	            	locations.add(values[i]);
	            	//System.out.print(n+" "+keyInit[n]+" ");
	            }
	        }
	        //System.out.println(locations.size());
	        keyInit=new String[locations.size()];
	        String[] array = (String[])locations.toArray(new String[locations.size()]);
	       // System.out.println("keyInit.length:"+keyInit.length);
	        //System.out.println("array.length:"+array.length);
	        for(int i=0;i<array.length;i++)
	        {
	        	//System.out.println(array[i]+" ");
	        	keyInit[i]=array[i];
	        	//System.out.println(keyInit[i]+" ");
	        }
	        
	        //System.out.print("keyInit.length:"+keyInit.length);
	       // for(int i=0;i<keyInit.length;i++)
	        //{
	        	//System.out.print(keyInit[i]+" ");
	        //}
	        dealData.LatInit();
			System.out.println("地点信息初始化完成！");
			System.out.println("距离信息初始化中...");
			dealData.costInit();
			System.out.println("距离信息初始化完成！");
		    GA_test test = new GA_test(); 
		    test.setDdWindow(new DynamicDataWindow("遗传算法最优化求解过程"));
		    test.caculate();  
		    List<String> dataBack=new ArrayList<String>();
		    List<Map<String, String>> newLocLatitudes=new ArrayList<Map<String,String>>();
		    for(int i=0;i<locations.size();i++)
		    {
		    	dataBack.add(keyInit[GA.geneRecord[i]]);
		    	newLocLatitudes.add(dealData.locLatitudes.get(GA.geneRecord[i]));
		    }
		    String[] backServlet=(String[])dataBack.toArray(new String[dataBack.size()]);
		    request.setAttribute("dataArray", backServlet);
		    request.setAttribute("dataMap", newLocLatitudes);
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		    System.out.println("算法执行完成！");
        }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
