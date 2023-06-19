//package com.project.sul.service;
//
//import com.project.sul.entity.Item;
//import com.project.sul.entity.Review;
//import com.project.sul.repository.ItemRepository;
//import com.project.sul.repository.ReviewRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@Transactional
//class ReviewServiceTest {
//    @Mock
//    private ReviewRepository reviewRepository;
//    @Mock
//    private ItemRepository itemRepository;
//    @InjectMocks
//    private ReviewService reviewService;
//    public ReviewServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//    @Test
//    void calAvgStar() {
//        Item item = new Item();
//        item.setItemNm("Test Item");
//
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(new Review(1L, null, item, 4.0, LocalDateTime.now(), "Comment1"));
//        reviews.add(new Review(2L, null, item, 5.0, LocalDateTime.now(), "Comment2"));
//        reviews.add(new Review(3L, null, item, 4.5, LocalDateTime.now(), "Comment3"));
//
//        when(reviewRepository.findByItemItemNm(item.getItemNm())).thenReturn(reviews);
//
//        Double avgStar = reviewService.calAvgStar(item);
//
//        Assertions.assertEquals(4.5, avgStar);
//    }
//
//    @Test
//    void callUpdateAvgStar() {
//    }
//
//    @Test
//    void updateAvgStar() {
//    }
//}