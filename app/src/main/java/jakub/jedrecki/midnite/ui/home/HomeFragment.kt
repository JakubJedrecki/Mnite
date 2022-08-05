package jakub.jedrecki.midnite.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jakub.jedrecki.midnite.databinding.FragmentHomeBinding
import jakub.jedrecki.midnite.domain.model.Match

@AndroidEntryPoint
class HomeFragment : Fragment(), MatchesListAdapter.MatchClickListener {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var matchesAdapter: MatchesListAdapter
    private val matches: MutableList<Match> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchesAdapter = MatchesListAdapter(matches, this)
        binding.rvMatches.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMatches.adapter = matchesAdapter

        setUpObservers()
        viewModel.getUpcomingMatches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), getString(it), Snackbar.LENGTH_LONG).show()
        }

        viewModel.upcomingMatches.observe(viewLifecycleOwner) { newMatches ->
            Log.d("homeFragment", "$newMatches")
            matches.addAll(newMatches.data)
            matchesAdapter.notifyDataSetChanged()
        }
    }

    override fun onMatchClick(match: Match) {
//        Toast.makeText(requireContext(), "not implemented", Toast.LENGTH_LONG).show()
        findNavController().navigate(HomeFragmentDirections.actionFirstFragmentToSecondFragment(match.id))
    }
}