package sky.pro.APILesson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.APILesson.model.Book;
import sky.pro.APILesson.service.BookService;

import java.util.Collection;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}") // http://localhost:8080/books/1 - Get
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping()// http://localhost:8080/books - Post

    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping // http://localhost:8080/books - edit
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping("{id}") // http://localhost:8080/books/1 - delete
    public Book removeBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

}
