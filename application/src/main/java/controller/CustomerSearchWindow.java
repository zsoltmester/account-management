package controller;

import model.Session;
import model.manager.CustomerManager;
import resource.Dimensions;
import resource.Strings;
import util.SearchUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The customer search window of the application. Read more in the documentation.
 */
public class CustomerSearchWindow extends Window {

    private static final String OPTION_PROPERTY_KEY = CustomerSearchWindow.class.getName() + ":OPTION_PROPERTY_KEY";

    private JTextField searchField;
    private JButton searchButton;
    private ButtonGroup chooser;
    private List<JRadioButton> options;
    private JButton okButton;

    /**
     * Displays the customer search window.
     *
     * @param session The current session.
     */
    public CustomerSearchWindow(Session session) {
        super(Strings.CUSTOMER_SEARCH_WINDOW_TITLE, session);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        searchField = new JTextField(Strings.CUSTOMER_SEARCH_SEARCH_FIELD_DEFAULT);
        addComponentToBoxLayout(searchField, Dimensions.CUSTOMER_SEARCH_WINDOW_COMPONENT_SIZE);
        searchButton = new JButton(Strings.CUSTOMER_SEARCH_SEARCH_BUTTON_TITLE);
        searchButton.addActionListener(new OnSearchButtonClickListener());
        addComponentToBoxLayout(searchButton, Dimensions.CUSTOMER_SEARCH_WINDOW_COMPONENT_SIZE);
        chooser = new ButtonGroup();
        okButton = new JButton(Strings.OK);
        okButton.addActionListener(new OnOkButtonClickListener());
        okButton.setEnabled(false);
        addComponentToBoxLayout(okButton, Dimensions.CUSTOMER_SEARCH_WINDOW_COMPONENT_SIZE);

        display(Dimensions.CUSTOMER_SEARCH_WINDOW_SIZE.width, Dimensions.CUSTOMER_SEARCH_WINDOW_SIZE.height);
    }

    private class OnSearchButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (options != null) {
                for (JRadioButton option : options) {
                    chooser.remove(option);
                    container.remove(option);
                }
                options = null;
                okButton.setEnabled(false);
                repaint();
            }

            Map<Long, String> strippedCustomers = CustomerManager.getStrippedCustomers();
            Set<Long> founded = SearchUtil.searchForCustomer(strippedCustomers, searchField.getText());
            if (founded.isEmpty()) {
                JOptionPane.showMessageDialog(container, Strings.CUSTOMER_SEARCH_NOT_FOUND_MESSAGE,
                        Strings.CUSTOMER_SEARCH_NOT_FOUND_TITLE, JOptionPane.WARNING_MESSAGE);
                return;
            }

            options = new ArrayList<>(founded.size());
            for (Long foundedId : founded) {
                JRadioButton option = new JRadioButton(String.format(Strings.CUSTOMER_SEARCH_RESULT_LINE,
                        strippedCustomers.get(foundedId), foundedId));
                option.putClientProperty(OPTION_PROPERTY_KEY, foundedId);
                options.add(option);
                chooser.add(option);
                addComponentToBoxLayout(option, Dimensions.CUSTOMER_SEARCH_WINDOW_OPTION_SIZE);
            }
            okButton.setEnabled(true);
            repaint();
        }
    }

    private class OnOkButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selected = null;
            for (JRadioButton option : options) {
                if (option.isSelected()) {
                    selected = option;
                    break;
                }
            }

            if (selected == null) {
                JOptionPane.showMessageDialog(container, Strings.CUSTOMER_SEARCH_NO_SELECTION,
                        null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            close();
            new CustomerManagerWindow(session,
                    CustomerManager.getCustomer((Long) selected.getClientProperty(OPTION_PROPERTY_KEY)));
        }
    }
}
