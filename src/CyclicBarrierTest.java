import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier同步工具应用(旅游集合乘车方案，设置三个集合点，每个集合点当有三个人才继续行驶，不然就处于等待)
 * 
 * @author 谭飞
 * @date 2012-02-07
 */
public class CyclicBarrierTest
{

	private final static int MAX_AVAILABLE = 3;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ExecutorService threadPools = Executors.newCachedThreadPool();// 创建线程池
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(MAX_AVAILABLE);

		for (int i = 0; i < MAX_AVAILABLE; i++)
		{
			Runnable runnable = new Runnable()
			{

				@Override
				public void run()
				{
					try
					{
						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达集合点1"
										+ "当前已有"
										+ (cyclicBarrier.getNumberWaiting() + 1)
										+ "个"
										+ ((cyclicBarrier.getNumberWaiting() != (MAX_AVAILABLE - 1)) ? "已经到达,正在等候..."
												: "已经到达,全部到齐，继续走咯"));
						cyclicBarrier.await();

						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达集合点2"
										+ "当前已有"
										+ (cyclicBarrier.getNumberWaiting() + 1)
										+ "个"
										+ ((cyclicBarrier.getNumberWaiting() != (MAX_AVAILABLE - 1)) ? "已经到达,正在等候..."
												: "已经到达,全部到齐，继续走咯"));
						cyclicBarrier.await();

						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达集合点3"
										+ "当前已有"
										+ (cyclicBarrier.getNumberWaiting() + 1)
										+ "个"
										+ ((cyclicBarrier.getNumberWaiting() != (MAX_AVAILABLE - 1)) ? "已经到达,正在等候..."
												: "已经到达,全部到齐，继续走咯"));
						cyclicBarrier.await();
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					} catch (BrokenBarrierException e)
					{
						e.printStackTrace();
					}
				}
			};
			threadPools.execute(runnable);
		}
		threadPools.shutdown();
	}

}
