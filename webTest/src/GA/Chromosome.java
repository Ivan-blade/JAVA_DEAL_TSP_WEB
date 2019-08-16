package GA;

import java.util.Random;
import java.util.ArrayList;  
import java.util.List;  
  
public class Chromosome { 
    public int[] gene=new int[TestWeb.keyInit.length];//��������  
    private double score;//��Ӧ�ĺ����÷�  
    public static double[][] costData=new double[TestWeb.keyInit.length][TestWeb.keyInit.length];
    public double getScore() {  
        return score;  
    }  
  
    public void setScore(double score) {  
        this.score = score;  
    }  
  
     /* ����һ����Ⱦɫ�� 
     */  
    public Chromosome() {  
    	//�������е�ֵ��0-x��ʾx����ͬ�ĵص㣬Ⱦɫ���ʼ��������ʮ��ֵ���ظ��ķŽ�����
    	int[] a = new int[TestWeb.keyInit.length];
    	for(int i=0;i<TestWeb.keyInit.length;i++)
    	{
    		a[i]=i;
    	}
    	Random r=new Random();
    	int max=a.length;
    	for (int i = 0; i < a.length; i++) {  
            int index=r.nextInt(max);
    		gene[i] = a[index];
    		a[index]=a[--max];
        } 
    }  
      
    /** 
     * @param c 
     * @return 
     * @Description: ��¡���� 
     */  
    public static Chromosome clone(final Chromosome c) {  
        if (c == null || c.gene == null) {  
            return null;  
        }  
        Chromosome copy = new Chromosome();  
        for (int i = 0; i < c.gene.length; i++) {  
            copy.gene[i] = c.gene[i];  
        }  
        return copy;  
    }  
            
    /** 
     * @param c1 
     * @param c2 
     * @Description: �Ŵ�������һ�� 
     */  
    //Ⱦɫ�彻�书��
    public static List<Chromosome> genetic(Chromosome p1, Chromosome p2) {  
        if (p1 == null || p2 == null) { //Ⱦɫ����һ��Ϊ�գ���������һ��  
            return null;  
        }  
        if (p1.gene == null || p2.gene == null) { //Ⱦɫ����һ��û�л������У���������һ��  
            return null;  
        }  
        if (p1.gene.length != p2.gene.length) { //Ⱦɫ��������г��Ȳ�ͬ����������һ��  
            return null;  
        }  
        Chromosome c1 = clone(p1);  
        Chromosome c2 = clone(p2);  
        //����������滥��λ������  
        int size = c1.gene.length;  
        int a = ((int) (Math.random() * size)) % size;  
        int b = ((int) (Math.random() * size)) % size;  
        int min = a > b ? b : a;  
        int max = a > b ? a : b;  
   
        int[] temp_1=new int[c1.gene.length];
        int[] temp_2=new int[max-min+1];
        int[] temp_3=new int[c1.gene.length-max+min-1];
        int[] temp_4=c1.gene;
        int n_2=0;
        int n_3=0;
        //temp_4����c1�Ļ������У��ڲ����Ӵ�Ⱦɫ��֮ǰ���ٶԸ���Ⱦɫ��ĸı䣬�����޸Ķ���temp_4�Ͻ��У����ֱ�Ӹ�ֵ��c1Ⱦɫ��
        //temp_1�����������Ľ�������temp_2������c2����Ƭ�ε�����
        //��c2�Ľ���Ƭ�α�����temp_1��
        for (int i = min; i <= max; i++) {
        	temp_1[i] = c2.gene[i];  
        	temp_2[n_2]=c2.gene[i];
        	n_2++;
        }  
        //��temp_4�Ļ���������ֵ�뽻��Ƭ����ͬ��ת����-1
        for(int i=0;i<c1.gene.length;i++)
        {
        	for(int j=min;j<=max;j++)
        	{
        		if(temp_4[i]==c2.gene[j])
        		{
        			temp_4[i]=-1;
        		}
        	}
        }
        //��temp_4�в�Ϊ-1�Ļ�����temp_3����
        for(int i=0;i<c1.gene.length;i++)
        {
        	if(temp_4[i]!=-1)
        	{
        		temp_3[n_3++]=temp_4[i];
        	}
        }
        //���ڽ���Ƭ�β�ͬ�Ļ�������ֵ��temp_1,minǰһ�κ�max��һ��
        for(int i=0;i<min;i++)
        {
        	temp_1[i]=temp_3[--n_3];//���Լ��ٳ䵱����
        }
        for(int i=max+1;i<c1.gene.length;i++)
        {
        	temp_1[i]=temp_3[--n_3];
        }
        c1.gene=temp_1;
        
        
        
        List<Chromosome> list = new ArrayList<Chromosome>();  
        list.add(c1);    
        return list;  
    }  
      
    /** 
     * @param num 
     * @Description: ����num��λ�÷������� 
     */  
    public void mutation(int num) {  
        //�������  
        int size = gene.length;  
        for (int i = 0; i < num; i++) {  
            //Ѱ�ұ���λ��  
            int at1 = ((int) (Math.random() * size)) % size;  
            int at2 = ((int) (Math.random() * size)) % size;  
            //������ֵ  
            //��������λ�û���
            int temp=gene[at1];
            gene[at1]=gene[at2];
            gene[at2]=temp;
        }  
    }  
      
    /** 
     * @return 
     * @Description: ������ת��Ϊ��Ӧ������ 
     */  
    //������ת����·��������[0][1][2][3][4][5][6][7][8][9]����0-1-2-3------9-0����·����ͨ��ǰ�������ʵ��ת��
    public double getNum() {  
        if (gene == null) {  
            return 0; 
        }
        int sum=0;
        int x_temp;
        int y_temp;
        for(int i=0;i<gene.length;i++)
        {
        	x_temp=gene[i];
        	y_temp=gene[(i+1)%(gene.length)];
        	sum+=costData[x_temp][y_temp];
        }
        return sum;
    }
}  
