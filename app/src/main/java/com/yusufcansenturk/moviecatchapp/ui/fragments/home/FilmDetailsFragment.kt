package com.yusufcansenturk.moviecatchapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFilimDetailsBinding
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionData
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteData
import com.yusufcansenturk.moviecatchapp.di.dao.watchList.WatchData
import com.yusufcansenturk.moviecatchapp.util.Constants
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.Collection
import com.yusufcansenturk.moviecatchapp.util.toast
import com.yusufcansenturk.moviecatchapp.viewmodel.DetailViewModel
import com.yusufcansenturk.moviecatchapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode

@AndroidEntryPoint
class FilmDetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilimDetailsBinding
    private val viewModel: DetailViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return if (this::binding.isInitialized){
            binding.root
        }else {
            binding = FragmentFilimDetailsBinding.inflate(layoutInflater)
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val movie_id = it.getInt("movieId")
            viewModel.getMovieDetail(movie_id)
        }

        movieDetailsObserver()
        favoriteViewModel.loadCollection()

        binding.btnMovieFavoriteList.setOnClickListener {
            viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
                favoriteViewModel.addFavoriteMovie(
                    FavoriteData(
                        movie_id = movieDetail.id,
                        imdb_id = movieDetail.imdb_id,
                        name = movieDetail.original_title,
                        runtime = movieDetail.runtime,
                        backdrop_path = movieDetail.backdrop_path,
                        poster_path = movieDetail.poster_path,
                        overview = movieDetail.overview,
                        vote_average = movieDetail.vote_average,
                        vote_count = movieDetail.vote_count,
                        favorite_status = true
                    )
                )
            }
        }

        val checkedItem = 1
        binding.btnAddMovieList.setOnClickListener {
            val collectionList = favoriteViewModel.collection.value!!
            val collectionNames = collectionList.map { it.collectionName }.toTypedArray()

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose collection")
                .setNeutralButton("cancel") { dialog, which ->

                }
                .setSingleChoiceItems(collectionNames, checkedItem) { dialog, which ->
                    viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
                        favoriteViewModel.addMovieToCollection(
                            CollectionData(
                                collectionName = collectionList[which].collectionName,
                                movie_id = movieDetail.id,
                                imdb_id = movieDetail.imdb_id,
                                name = movieDetail.title,
                                runtime = movieDetail.runtime,
                                backdrop_path = movieDetail.backdrop_path,
                                poster_path = movieDetail.poster_path,
                                overview = movieDetail.overview,
                                vote_average = movieDetail.vote_average,
                                vote_count = movieDetail.vote_count,
                                collection_status = true
                            )
                        )
                        toast("The film has been successfully registered in the collection.")
                    }
                }
                .show()

        }

        binding.btnAddSave.setOnClickListener {
            viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
                movieDetail.let {
                    favoriteViewModel.addMovieWatchList(
                        WatchData(
                            movie_id = movieDetail.id,
                            imdb_id = movieDetail.imdb_id,
                            name = movieDetail.title,
                            backdrop_path = movieDetail.backdrop_path,
                            poster_path = movieDetail.poster_path,
                            watch_status = true
                        )
                    )
                }
            }
        }


    }

    private fun movieDetailsObserver() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            binding.txtImdb.text = movieDetail.vote_average.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble().toString()
            val genreStringBuilder = StringBuilder()

            for (genre in movieDetail.genres) {
                genreStringBuilder.append(genre.name)
                genreStringBuilder.append(", ")
            }

            val genresText = genreStringBuilder.toString().trimEnd(',', ' ')

            binding.txtGenres.text = genresText

            binding.txtMovieTitle.text = movieDetail.original_title.toString()
            binding.txtMovieDescripton.text = movieDetail.overview.toString()
            binding.txtReleaseDate.text = movieDetail.release_date
            Glide.with(binding.imgMovieBackground).load(Constants.BASE_IMAGE_URL +movieDetail.backdrop_path).into(binding.imgMovieBackground)
            Glide.with(binding.imgMoviePoster).load(Constants.BASE_IMAGE_URL +movieDetail.poster_path).into(binding.imgMoviePoster)
        }
    }

}