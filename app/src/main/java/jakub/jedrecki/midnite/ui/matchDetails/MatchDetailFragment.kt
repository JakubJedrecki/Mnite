package jakub.jedrecki.midnite.ui.matchDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jakub.jedrecki.midnite.databinding.FragmentMatchDetailsBinding

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {

    private val viewModel: MatchDetailViewModel by viewModels()

    private var _binding: FragmentMatchDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MatchDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

        viewModel.getMatchDetail(args.matchId)
//        viewModel.getMatchDetail(519625)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), getString(it), Snackbar.LENGTH_LONG).show()
        }

        viewModel.matchDetails.observe(viewLifecycleOwner) {

            //fixme
            //due to limited time this is oversimplified approach to put anything in UI
            //given more time it would be a list of markets with list of contracts inside

            binding.tvHomeTeamName.text = it.homeTeam
            binding.tvAwayTeamName.text = it.awayTeam

            if (it.markets.isNotEmpty()) {
                val market = it.markets[0]
                binding.market1.tvMarketName.text = market.name

                if (market.contracts.isNotEmpty()) {
                    val contract = market.contracts[0]
                    binding.market1.tvContract1Name.text = contract.name
                    binding.market1.tvContract1Price.text = contract.price
                }

                if (market.contracts.size >= 2) {
                    val contract = market.contracts[1]
                    binding.market1.tvContract2Name.text = contract.name
                    binding.market1.tvContract2Price.text = contract.price
                }
            }

                if (it.markets.size >= 2) {
                    val market2 = it.markets[1]
                    binding.market2.tvMarketName.text = market2.name

                    if (market2.contracts.isNotEmpty()) {
                        val contract = market2.contracts[0]
                        binding.market2.tvContract1Name.text = contract.name
                        binding.market2.tvContract1Price.text = contract.price
                    }

                    if (market2.contracts.size >= 2) {
                        val contract = market2.contracts[1]
                        binding.market2.tvContract2Name.text = contract.name
                        binding.market2.tvContract2Price.text = contract.price
                    }
                }

            binding.layLoading.visibility = View.GONE
        }
    }
}