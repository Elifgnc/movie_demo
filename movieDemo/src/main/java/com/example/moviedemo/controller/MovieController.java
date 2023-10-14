package com.example.moviedemo.controller;

import com.example.moviedemo.business.abstracts.MovieService;
import com.example.moviedemo.dataAccess.MovieRepository;
import com.example.moviedemo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
    @RequestMapping("/movies") //bir Controller sınıfının belirli bir yol altında
    // hangi HTTP isteklerini ele alacağını belirtmek için kullanılır.

    public class MovieController{
        private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired // bir nesnenin otomatik oluşturulmasını ve enjekte edilmesini sağlar.
        public MovieController(MovieService movieService, MovieRepository movieRepository) {
            this.movieService = movieService;
        }

        @GetMapping("/list") // listeleme için kullandık.
        public Iterable<Movie> list() { //public Iterable<Movie> list(): Bu, bir liste alma işlemi için kullanılan bir metodun imzasıdır.
            // Metot, geriye Iterable<Movie> türünde bir nesne döndürür.
            return movieService.getAllMovies();

            //Genel olarak, bu kod, "/list" yoluyla gelen GET isteklerini ele alarak
            // tüm filmleri listeleyen bir metodun imzasını ve işlevini tanımlar.
            // Metot, veritabanından tüm filmleri almak için bir hizmeti çağırır
            //ve dönen sonucu Iterable<Movie> türünde bir nesne olarak döndürür.

        }

        @PostMapping("/add_movie") // ekleme işlemi için kullandık
        public ResponseEntity<Movie> saveMoviee(@RequestBody Movie movie) {
            return new ResponseEntity<Movie>(this.movieService.save(movie), HttpStatus.CREATED);

            // return new ResponseEntity<Movie>(this.movieService.save(movie), HttpStatus.CREATED);: Bu satır, yeni bir ResponseEntity nesnesi oluşturur ve döndürür.
            //this.movieService.save(movie): Bu, bir hizmetin (movieService olarak adlandırılan) save metodunu çağırarak yeni bir film ekler ve eklenen filmi döndürür.
            //HttpStatus.CREATED: Bu, HTTP yanıt durumunun 201 Created olarak ayarlanması gerektiğini belirtir. Bu durum, yeni bir kaynak başarıyla oluşturulduğunda kullanılır.
        }

        @PutMapping("/{id}") // güncelleme işlemi için kullanılır.
        public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
            //@RequestBody anotasyonu ile işaretlenen Movie parametresi, HTTP isteğinin gövdesindeki veriyi Movie türünden bir nesneye otomatik olarak dönüştürür.
            // Bu sayede kolayca güncelleme işlemini gerçekleştirebiliriz.

        return new ResponseEntity<Movie>(this.movieService.updateMovie(id, movie), HttpStatus.OK);
             // Genel olarak, bu kodun amacı, bir film kaynağını güncellemek için PUT isteklerini yönetmektir
            // URL yolundan film kimliğini çıkarır, gelen JSON verisini bir Movie nesnesine çözümler
            // ve gerçek güncellemeyi yapmak için bir hizmeti kullanır. Son olarak, güncellenmiş film nesnesi
            // ve 200 OK durum kodu ile bir HTTP yanıtı döndürür.
        }

      @DeleteMapping("{id}")
      public ResponseEntity<String> deleteMovie(@PathVariable("id") long id) {
            //@PathVariable("id") long id: Bu parametre, @PathVariable anotasyonu ile işaretlenmiş
          // ve metoda gelen URL yolundaki "id" parametresini alır.
          // Bu parametre, silinecek filmin kimliğini temsil eder.
        this.movieService.deleteMovie(id);
        //veritabanından ilgili filmi kaldırma işlemini gerçekleştirir.
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
        //"Deleted.": Bu, silme işleminin başarılı olduğunu belirten bir metin mesajıdır.
          //HttpStatus.OK: Bu, HTTP yanıt durumunun 200 OK olarak ayarlanması gerektiğini belirtir.
          // Bu durum, silme işleminin başarılı olduğunu belirtmek için kullanılır.
    }

    @GetMapping("/search/{keyword}")
    public Iterable<Movie> search(@PathVariable("keyword") String keyword)

    //Bu satır, search adında bir public erişim belirleyiciye sahip bir fonksiyon tanımlar.
    // Bu fonksiyon, @PathVariable anotasyonunu kullanarak URL içindeki {keyword} değerini
    // String türünde keyword parametresine bağlar.
    // Fonksiyon sonucunda, Iterable arayüzü türünde bir nesne döndürecek.
    {
        return movieService.search(keyword);
    }

       // keyworde göre arama :

   /* @GetMapping("/filter/{keyword}")
    public Iterable<Movie> filter(@PathVariable("keyword") String keyword) {
        return movieService.filter(keyword);
    }*/


        // type & director içi arama :
    //@PostMapping("/filter2")
    //@ResponseBody
    //public Iterable<Movie> filter3(@RequestParam(value = "type", required=false) String type, @RequestParam(value = "director", required=false) String director) {
      //  return movieService.filter3(type,director);
    //}

         //imdbRatingMin & imdbRatingMax için arama :
    //@PostMapping("/filter4")
    //@ResponseBody
    //public Iterable<Movie> filter4(@RequestParam(value = "imdbRatingMin", required=false) float imdbRatingMin, @RequestParam(value = "imdbRatingMax", required=false) float imdbRatingMax) {
      //  return movieService.filter4( imdbRatingMin, imdbRatingMax);
    //}

    @PostMapping("/filter5")
    @ResponseBody
    public Iterable<Movie> filter5(@RequestParam(value = "imdbRatingMin", required=false) float imdbRatingMin,
                                   @RequestParam(value = "imdbRatingMax", required=false) float imdbRatingMax,
                                   @RequestParam(value = "type", required=false) String type,
                                   @RequestParam(value = "director", required=false) String director) {
        return movieService.filter5( type,director,imdbRatingMin,imdbRatingMax);
    }


}
