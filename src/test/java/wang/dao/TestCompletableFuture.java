package wang.dao;

import java.util.concurrent.*;

public class TestCompletableFuture {

    public static ExecutorService executorService = new ThreadPoolExecutor(
            10,
            100,
            30L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static ArticleVO asyncReturn(){
        ArticleVO article = new ArticleVO();
        long startTime=System.currentTimeMillis();
        CompletableFuture<ArticleVO> articleContext = CompletableFuture.supplyAsync(() -> {
            try {
                article.setId(1L);
                article.setContent("我是宁在春写的文章内容");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return article;

        }, executorService);

        CompletableFuture<Void> author = articleContext.thenAcceptAsync((res) -> {
            res.setAuthor("的作者是王凤霞");
        }, executorService);

        CompletableFuture<Void> futureAll = CompletableFuture.allOf(articleContext, author);

        try {
            futureAll.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("耗费的总时间===>"+(endTime-startTime));
        executorService.shutdown();
        return article;
    }

    public static void main(String[] args) {
        ArticleVO articleVO = asyncReturn();
        System.out.println(articleVO);
    }

}
 class ArticleVO{
    private Long id;
    private String content;
    private String author;

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }
 }