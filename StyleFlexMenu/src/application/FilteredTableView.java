package application;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;

public class FilteredTableView<S> extends TableView<S> {

	private Map<Integer, Predicate<S>> predicates;
	private FilteredList<S> dataList;

	public FilteredTableView() {
		this(Collections.emptyList());
	}

	/**
	 * Creates a new table that filters its data.
	 * <p>
	 * <br>
	 * <strong>If you want this to work, do not call
	 * {@link #setItems(ObservableList)}!</strong> <br>
	 * I can't prevent you from doing that, as the method is final.
	 *
	 * @param dataList
	 *            the data to display initially
	 */

	public FilteredTableView(List<S> dataList) {
		this.dataList = new FilteredList<>(FXCollections.observableArrayList(dataList));
		this.predicates = new HashMap<>();

		// we set that now and pray they don't change it
		setItems(this.dataList);
	}

	public void setDataList(List<S> dataList) {
		this.dataList = new FilteredList<>(FXCollections.observableArrayList(dataList));
		setItems(this.dataList);
	}

	/**
	 * Sets the predicate with the given ID to the passed one. Any previous with
	 * this id will be overwritten and the filter will be re-run.
	 *
	 * @param id
	 *            the id of the predicate. Just to distinguish different ones
	 * @param predicate
	 *            the predicate to filter with
	 */
	public void setPredicate(int id, Predicate<S> predicate) {
		predicates.put(id, predicate);

		rerunFilter();
	}

	/**
	 * Removes a predicate with the given id and filters again.
	 *
	 * @param id
	 *            the id of the predicate to remove
	 */
	public void removePredicate(int id) {
		predicates.remove(id);

		rerunFilter();
	}

	/**
	 * Runs the filter again by building a new updated predicate and assigning that
	 * to the list.
	 */
	private void rerunFilter() {
		dataList.setPredicate(s -> {
			for (Predicate<S> predicate : predicates.values()) {
				if (!predicate.test(s)) {
					return false;
				}
			}

			return true;
		});
	}

	/**
	 * Runs the filter again by building a new updated predicate and assigning that
	 * to the list.
	 */
	private void rerunFilterMoreFunctional() {
		dataList.setPredicate(s -> predicates.values().stream().allMatch(pred -> pred.test(s)));
	}

	/**
	 * Runs the filter again by building a new updated predicate and assigning that
	 * to the list.
	 */
	private void rerunFilterEvenMoreFunctional() {
		dataList.setPredicate(predicates.values().stream().reduce(Predicate::and).orElse(s -> true) // default is just
																									// not filtering at
																									// all, if no
																									// predicate is
																									// registered
		);
	}
}
