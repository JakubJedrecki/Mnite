package jakub.jedrecki.midnite.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jakub.jedrecki.midnite.databinding.ItemMatchBinding
import jakub.jedrecki.midnite.domain.model.Match

class MatchesListAdapter(
    private val matches: List<Match>,
    private val clickListener: MatchClickListener
): RecyclerView.Adapter<MatchesListAdapter.ViewHolder>() {

    private lateinit var binding: ItemMatchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = matches[position]
        holder.binding.match = item
        holder.binding.clickListener = clickListener

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = matches.size

    inner class ViewHolder(val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root)

    interface MatchClickListener {
        fun onMatchClick(match: Match)
    }
}