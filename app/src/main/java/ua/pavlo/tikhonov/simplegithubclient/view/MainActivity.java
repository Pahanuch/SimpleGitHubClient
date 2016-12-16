package ua.pavlo.tikhonov.simplegithubclient.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import butterknife.Bind;
import butterknife.ButterKnife;
import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.model.api.basic.Auth;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.CommitsFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.LoginFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.ProfileFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.RepoInfoFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.RepoListFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private static String TAG = "TAG";

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.toolbar_progress_bar)
    protected ProgressBar progressBar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null)
            if (Auth.isAuth(this))
                replaceFragment(new ProfileFragment(), false);
            else replaceFragment(new LoginFragment(), false);

    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_down, R.anim.exit_to_top);
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void startRepoInfoFragment(Repository repository) {
        replaceFragment(RepoInfoFragment.newInstance(repository), true);
    }

    @Override
    public void startCommitsFragment(Repository repository) {
        replaceFragment(CommitsFragment.newInstance(repository), true);
    }

    @Override
    public void startRepoListFragment(User user) {
        replaceFragment(RepoListFragment.newInstance(user), true);
    }

    @Override
    public void startLoginFragment() {
        replaceFragment(LoginFragment.newInstance(), false);
    }

    @Override
    public void startProfileFragment() {
        replaceFragment(ProfileFragment.newInstance(), false);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setTitle(String title, boolean addBackButton) {
        getSupportActionBar().setTitle(title);
        if (addBackButton) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        else getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
